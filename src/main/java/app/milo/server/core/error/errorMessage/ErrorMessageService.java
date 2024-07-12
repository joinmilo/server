package app.milo.server.core.error.errorMessage;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.TokenExpiredException;
import app.milo.server.core.base.DataService;
import app.milo.server.core.error.ErrorMailService;
import app.milo.server.core.error.exception.AlreadyVerifiedException;
import app.milo.server.core.error.exception.BadParamsException;
import app.milo.server.core.error.exception.DuplicateException;
import app.milo.server.core.error.exception.InvalidCaptchaException;
import app.milo.server.core.error.exception.InvalidPasswordResetException;
import app.milo.server.core.error.exception.InvalidTokenException;
import app.milo.server.core.error.exception.NotDeletableException;
import app.milo.server.core.error.exception.NotFoundException;
import app.milo.server.core.error.exception.NotNullableException;
import app.milo.server.core.error.exception.VerificationInvalidException;
import app.milo.server.core.error.exception.VerificationPendingException;
import app.milo.server.core.error.exception.VerificationUserNotFoundException;
import app.milo.server.core.repository.DataRepository;

@Service
public class ErrorMessageService
    extends DataService<ErrorMessageEntity, ErrorMessagePredicateBuilder> {

  private ErrorMailService errorMailService;

  public ErrorMessageService(DataRepository<ErrorMessageEntity> repo,
      ErrorMessagePredicateBuilder predicate, ErrorMailService errorMailService) {
    super(repo, predicate);

    this.errorMailService = errorMailService;
  }

  // TODO: Get messages from DB
  public String getLocalizedMessageByException(Throwable exception) {

    if (exception instanceof AccessDeniedException) {
      return "Benutzer ist nicht authentifiziert. Logge dich ein.";
    }

    if (exception instanceof AlreadyVerifiedException) {
      return "Benutzer bereits verfiziert. Logge dich ein.";
    }

    if (exception instanceof BadParamsException) {
      return "Eingaben fehlerhaft. Probiere es mit anderen Eingaben.";
    }

    if (exception instanceof DuplicateException) {
      return "Ein Datensatz mit den Eingaben existiert bereits. Probiere es mit anderen Eingaben.";
    }

    if (exception instanceof InvalidPasswordResetException) {
      return "Passwort nicht zurück gesetzt. Generieren Sie eine neue Mail.";
    }

    if (exception instanceof InvalidTokenException) {
      return "Sicherheitstoken ungültig. Bitte neu einloggen.";
    }
    
    if (exception instanceof TokenExpiredException) {
      return "Sitzung abgelaufen. Bitte neu einloggen.";
    }

    if (exception instanceof BadCredentialsException
        || exception instanceof InternalAuthenticationServiceException) {
      return "Benutzername und Passwort falsch";
    }

    if (exception instanceof InvalidCaptchaException) {
      return "Captcha-Verifikation fehlgeschlagen. Bitte probieren Sie es erneut.";
    }

    if (exception instanceof VerificationInvalidException) {
      return "Verifizierung ungültig. Entweder ist Verifizierung abgelaufen oder bereits verifiziert.";
    }
    
    if (exception instanceof VerificationPendingException) {
      return "Email noch nicht verifiziert.";
    }
    
    if (exception instanceof VerificationUserNotFoundException) {
      return "Email existiert nicht. Bitte korrekte Email angeben oder registrieren.";
    }

    if (exception instanceof NotDeletableException) {
      return "Inhalt konnte nicht gelöscht werden, da Referenz auf andere Inhalte existiert. Lösche bitte zunächst die Referenz und probiere es erneut.";
    }

    if (exception instanceof NotFoundException) {
      return "Inhalt(e) konnte(n) nicht gefunden werden. Probiere es mit anderen Eingaben.";
    }

    if (exception instanceof NotNullableException) {
      return "Felder sind leer, die nicht leer sein dürfen.";
    }

    this.errorMailService.sendErrorMail(exception);
    return "Unbekannter Fehler. Wende dich bitte an den Support.";
  }

  public Boolean sendErrorMail(String stackTrace) throws Throwable {
    this.errorMailService.sendErrorMail(stackTrace);
    return true;
  }

}
