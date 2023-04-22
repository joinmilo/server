package app.wooportal.server.core.security.components.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.error.exception.InvalidPasswordResetException;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.error.exception.InvalidVerificationException;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.push.subscription.SubscriptionService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.emailVerification.VerificationEntity;
import app.wooportal.server.core.security.components.user.emailVerification.VerificationService;
import app.wooportal.server.core.security.components.user.passwordReset.PasswordResetEntity;
import app.wooportal.server.core.security.components.user.passwordReset.PasswordResetService;
import app.wooportal.server.core.security.services.AuthenticationService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class UserService extends DataService<UserEntity, UserPredicateBuilder> {

  private final AuthenticationService authService;

  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  private final MediaService mediaService;

  public UserService(DataRepository<UserEntity> repo, UserPredicateBuilder predicate,
      AuthenticationService authService, BCryptPasswordEncoder bcryptPasswordEncoder,
      MediaService mediaService, PasswordResetService passwordResetService,
      SubscriptionService subscriptionService, VerificationService verificationService) {
    super(repo, predicate);

    this.authService = authService;
    this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    this.mediaService = mediaService;

    addService("passwordResets", passwordResetService);
    addService("subscriptions", subscriptionService);
    addService("verifications", verificationService);
  }

  public Optional<UserEntity> getByMail(String name) {
    return repo.findOne(singleQuery(predicate.withMail(name)));
  }

  public Optional<UserEntity> me() {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      return repo.findOne(singleQuery(predicate.withId(currentUser.get().getId())));
    }
    return currentUser;
  }

  @Override
  public void preSave(UserEntity entity, UserEntity newEntity, JsonNode context) {
//todo terms accepted
      if (newEntity.getPassword() != null && newEntity.getId() == null) {
        newEntity.setPassword(bcryptPasswordEncoder.encode(newEntity.getPassword()));
      }
      if (entity.getId() == null || entity.getId().isBlank()) {
        newEntity.setVerifications(new HashSet<>(List.of(new VerificationEntity())));
        setContext("verifications", context);
        newEntity.setVerified(false);
        setContext("verified", context);
      }
  }

  public Optional<UserEntity> saveMe(UserEntity entity) {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      entity.setId(currentUser.get().getId());
      return Optional.of(saveWithContext(entity));
    }
    return currentUser;
  }

  public Optional<UserEntity> addUploads(List<MediaEntity> uploads) {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      repo.findOne(singleQuery(predicate.withId(currentUser.get().getId()))).get().getUserContext()
          .getUploads().addAll(mediaService.saveAll(uploads));
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }

  public Optional<UserEntity> deleteUpload(List<String> uploads) {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      mediaService.deleteById(uploads.toArray(new String[uploads.size()]));
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }

  public Boolean createPasswordReset(String email) {
    var result = repo.findOne(singleQuery(predicate.withMail(email)));

    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", email);
    }

    var copy = ReflectionUtils.copy(result.get());
    if (copy.getPasswordResets() != null) {
      copy.getPasswordResets().add(new PasswordResetEntity());
    } else {
      copy.setPasswordResets(new HashSet<>(List.of(new PasswordResetEntity())));
    }
    persist(result.get(), copy, createContext("passwordResets"));
    return true;
  }

  public Boolean resetPassword(String token, String password) {
    var passwordReset = getService(PasswordResetService.class).getByToken(token);
    if (passwordReset.isEmpty()) {
      throw new InvalidPasswordResetException("Password reset not requested", token);
    }
    var user = passwordReset.get().getUser();
    user.setPassword(bcryptPasswordEncoder.encode(password));
    var service = getService(PasswordResetService.class);
    service.deleteAll(service.collectionQuery(service.getPredicate().withUser(user.getId())));
    repo.save(user);
    return true;
  }

  public Boolean createVerification(String mailAddress) {
    var result = repo.findOne(singleQuery(predicate.withMail(mailAddress)));

    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", mailAddress);
    }

    if (result.get().getVerified()) {
      throw new AlreadyVerifiedException("Already verified");
    }

    var copy = ReflectionUtils.copy(result.get());
    if (copy.getVerifications() != null) {
      copy.getVerifications().add(new VerificationEntity());
    } else {
      copy.setVerifications(new HashSet<>(List.of(new VerificationEntity())));
    }
    persist(result.get(), copy, createContext("verifications"));
    return true;
  }

  public UserEntity verify(String token) {
    var verification = getService(VerificationService.class).getByKey(token);
    if (verification.isPresent()) {
      var user = verification.get().getUser();
      user.setVerified(true);
      var service = getService(VerificationService.class);
      service.deleteAll(service.collectionQuery(service.getPredicate().withUser(user.getId())));
      return repo.save(user);
    }
    throw new InvalidVerificationException("Verification invalid", token);
  }

  public Optional<UserEntity> deleteMe(String password) {
    var currentUser = authService.authenticateCurrentUser(password);
    if (currentUser.isPresent()) {
      deleteById(currentUser.get().getUser().getId());
      return Optional.of(currentUser.get().getUser());
    }
    return Optional.empty();
  }

  public boolean changePassword(String newPassword) {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      currentUser.get().setPassword(bcryptPasswordEncoder.encode(newPassword));
      repo.save(currentUser.get());
      return true;
    }
    throw new InvalidTokenException("Token is invalid");
  }


  public double checkPassword(String password) {

    var entropy = calculateEntropy(password);
    return entropy;
  }

  public static double calculateEntropy(String password) {
    var possibleCombinations = Math.pow(getCharacterSpaceSize(password), password.length());
    return (Math.log(possibleCombinations) / Math.log(2) + 1e-10);
  }

  public static int getCharacterSpaceSize(String password) {
    var characterSpaceSize = 0;
    
    if (Pattern.compile("\\p{Lower}").matcher(password).find()) {
      characterSpaceSize += 26; 
  }
  if (Pattern.compile("\\p{Upper}").matcher(password).find()) {
      characterSpaceSize += 26; 
  }
  if (Pattern.compile("\\p{Digit}").matcher(password).find()) {
      characterSpaceSize += 10; 
  }
  if (Pattern.compile("\\W").matcher(password).find()) {
      characterSpaceSize += 40; 
  }
  return characterSpaceSize;

  }
}

