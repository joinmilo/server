package app.wooportal.server.core.error.errorMessage;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.ErrorMailService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.error.exception.DuplicateException;
import app.wooportal.server.core.error.exception.InvalidPasswordResetException;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.error.exception.InvalidVerificationException;
import app.wooportal.server.core.error.exception.NotDeletableException;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.error.exception.NotNullableException;
import app.wooportal.server.core.repository.DataRepository;

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
  public String getLocalizedMessageByException(Exception e) throws Throwable {

    if (e instanceof AccessDeniedException) {
      return "Benutzer ist nicht authentifiziert. Logge dich ein.";
    }

    if (e instanceof AlreadyVerifiedException) {
      return "Benutzer bereits verfiziert. Logge dich ein.";
    }

    if (e instanceof BadParamsException) {
      return "Eingaben fehlerhaft. Probiere es mit anderen Eingaben.";
    }

    if (e instanceof DuplicateException) {
      return "Ein Datensatz mit den Eingaben existiert bereits. Probiere es mit anderen Eingaben.";
    }

    if (e instanceof InvalidPasswordResetException) {
      return "Passwort zurücksetzen hat nicht geklappt. Bitte erneut prüfen.";
    }

    if (e instanceof InvalidTokenException) {
      return "Sicherheitstoken ungültig. Bitte neu einloggen.";
    }

    if (e instanceof BadCredentialsException) {
      return "Benutzername und Passwort falsch";
    }

    if (e instanceof InvalidVerificationException) {
      return "Verifizierung ungültig. Bitte erneute Verifizierungsmail erzeugen.";
    }

    if (e instanceof NotDeletableException) {
      return "Inhalt konnte nicht gelöscht werden, da Referenz auf andere Inhalte existiert. Lösche bitte zunächst die Referenz und probiere es erneut.";
    }

    if (e instanceof NotFoundException) {
      return "Inhalt(e) konnte(n) nicht gefunden werden. Probiere es mit anderen Eingaben.";
    }

    if (e instanceof NotNullableException) {
      return "Felder sind leer, die nicht leer sein dürfen.";
    }

    this.errorMailService.sendErrorMail(e);
    return "Unbekannter Fehler. Wende dich bitte an den Support.";
  }

  public Boolean sendErrorMail(String stackTrace) throws Throwable {
    this.errorMailService.sendErrorMail(stackTrace);
    return true;
  }

}
