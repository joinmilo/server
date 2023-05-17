package app.wooportal.server.core.error.exception;

public class VerificationPendingException extends BaseException {

  private static final long serialVersionUID = 1L;

  public VerificationPendingException(String message, Object... params) {
    super(message, params);
  }
}
