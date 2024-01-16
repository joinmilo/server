package app.wooportal.server.core.security.components.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.userDeletion.base.UserDeletionEntity;
import app.wooportal.server.base.userDeletion.base.UserDeletionService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.error.exception.InvalidPasswordResetException;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.error.exception.VerificationInvalidException;
import app.wooportal.server.core.error.exception.VerificationUserNotFoundException;
import app.wooportal.server.core.i18n.components.language.LanguageEntity;
import app.wooportal.server.core.i18n.components.language.LanguageService;
import app.wooportal.server.core.i18n.translation.LocaleService;
import app.wooportal.server.core.push.subscription.SubscriptionService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.role.application.PrivilegeApplicationService;
import app.wooportal.server.core.security.components.role.base.RoleService;
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
  
  private final LocaleService localeService;
  
  private final LanguageService languageService;
  
  private final RoleService roleService;
  
  private final UserDeletionService userDeletionService;

  public UserService(
      DataRepository<UserEntity> repo,
      UserPredicateBuilder predicate,
      AuthenticationService authService,
      BCryptPasswordEncoder bcryptPasswordEncoder,
      LocaleService localeService,
      LanguageService languageService,
      PrivilegeApplicationService privilegeApplicationService,
      PasswordResetService passwordResetService,
      RoleService roleService,
      SubscriptionService subscriptionService,
      UserDeletionService userDeletionService,
      VerificationService verificationService) {
    super(repo, predicate);

    this.authService = authService;
    this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    this.localeService = localeService;
    this.languageService = languageService;
    this.roleService = roleService;
    this.userDeletionService = userDeletionService;

    addService("passwordResets", passwordResetService);
    addService("subscriptions", subscriptionService);
    addService("verifications", verificationService);
    addService("privilegeApplications", privilegeApplicationService);
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
  
  public Boolean addRole(
      String userId,
      String roleId) {
    var user = getById(userId);
    var role = roleService.getById(roleId);
    if (user.isPresent() && role.isPresent()
        && !user.get().getRoles().contains(role.get())) {
      user.get().getRoles().add(role.get());
      repo.save(user.get());
      return true;
    }
    return false;
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
      deleteById(currentUser.get().getUser().getId());
      userDeletionService.save(userDeletion);
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

}

