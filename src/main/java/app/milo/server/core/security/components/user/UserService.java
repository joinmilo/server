package app.milo.server.core.security.components.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.core.base.DataService;
import app.milo.server.core.error.exception.AlreadyVerifiedException;
import app.milo.server.core.error.exception.BadParamsException;
import app.milo.server.core.error.exception.InvalidPasswordResetException;
import app.milo.server.core.error.exception.InvalidTokenException;
import app.milo.server.core.error.exception.NotFoundException;
import app.milo.server.core.error.exception.VerificationInvalidException;
import app.milo.server.core.error.exception.VerificationUserNotFoundException;
import app.milo.server.core.i18n.components.language.LanguageEntity;
import app.milo.server.core.i18n.components.language.LanguageService;
import app.milo.server.core.i18n.translation.LocaleService;
import app.milo.server.core.push.subscription.SubscriptionService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.security.components.user.emailVerification.VerificationEntity;
import app.milo.server.core.security.components.user.emailVerification.VerificationService;
import app.milo.server.core.security.components.user.passwordReset.PasswordResetEntity;
import app.milo.server.core.security.components.user.passwordReset.PasswordResetService;
import app.milo.server.core.security.components.userDeletion.base.UserDeletionEntity;
import app.milo.server.core.security.components.userDeletion.base.UserDeletionService;
import app.milo.server.core.security.services.AuthenticationService;
import app.milo.server.core.utils.ReflectionUtils;

@Service
public class UserService extends DataService<UserEntity, UserPredicateBuilder> {

  private final AuthenticationService authService;

  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  private final LocaleService localeService;

  private final LanguageService languageService;

  private final UserDeletionService userDeletionService;

  public UserService(DataRepository<UserEntity> repo,
      UserPredicateBuilder predicate,
      AuthenticationService authService,
      BCryptPasswordEncoder bcryptPasswordEncoder,
      LocaleService localeService,
      LanguageService languageService,
      PasswordResetService passwordResetService, 
      SubscriptionService subscriptionService,
      UserDeletionService userDeletionService,
      VerificationService verificationService) {
    super(repo, predicate);

    this.authService = authService;
    this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    this.localeService = localeService;
    this.languageService = languageService;
    this.userDeletionService = userDeletionService;

    addService("passwordResets", passwordResetService);
    addService("subscriptions", subscriptionService);
    addService("verifications", verificationService);
  }

  public Optional<UserEntity> getByMail(String name) {
    return repo.findOne(singleQuery(predicate.withEmail(name)).addGraph(graph("roles.privileges")));
  }

  public Optional<UserEntity> me() {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      return repo.findOne(singleQuery(predicate.withId(currentUser.get().getId())));
    }
    return currentUser;
  }

  @Override
  public void preCreate(UserEntity entity, UserEntity newEntity, JsonNode context) {
    if (newEntity.getId() == null && !newEntity.getTermsAccepted()) {
      throw new BadParamsException("Terms were not accepted", newEntity);
    }

    if (newEntity.getPassword() != null && newEntity.getId() == null) {
      newEntity.setPassword(bcryptPasswordEncoder.encode(newEntity.getPassword()));
    }

    if (entity.getId() == null || entity.getId().isBlank()) {
      newEntity.setVerifications(new HashSet<>(List.of(new VerificationEntity())));
      addContext("verifications", context);
      newEntity.setVerified(false);
      addContext("verified", context);
    }

    var language = new LanguageEntity();
    language.setLocale(localeService.getDefaultLocale());
    var result = languageService.getByExample(language);
    if (result.isPresent()) {
      newEntity.setLanguage(result.get());
      addContext("language", context);
    }
  }

  public Boolean createPasswordReset(String email) {
    var result = repo.findOne(singleQuery(predicate.withEmail(email)));

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
    var result = repo.findOne(singleQuery(predicate.withEmail(mailAddress)));

    if (result.isEmpty()) {
      throw new VerificationUserNotFoundException("User with mail does not exist", mailAddress);
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
    throw new VerificationInvalidException("Verification invalid", token);
  }

  public Boolean deleteMe(String password, UserDeletionEntity userDeletion) {
    var currentUser = authService.authenticateCurrentUser(password);
    if (currentUser.isPresent()) {
      userDeletionService.save(userDeletion);
      deleteById(currentUser.get().getUser().getId());
      return true;
    }
    return false;
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

  //TODO: Remove once migrated to lib
  public double calculatePasswordEntropy(String password) {
    var possibleCombinations = Math.pow(getCharacterSpaceSize(password), password.length());
    return (Math.log(possibleCombinations) / Math.log(2) + 1e-10);
  }

  public int getCharacterSpaceSize(String password) {
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

  public List<UserEntity> getUsersWithPrivileges(String... privileges) {
    return repo.findAll(collectionQuery(predicate.withPrivilege(privileges))).getList();
  }
}

