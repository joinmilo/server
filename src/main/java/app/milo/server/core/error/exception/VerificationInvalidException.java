package app.milo.server.core.error.exception;

public class VerificationInvalidException extends BaseException {

  private static final long serialVersionUID = 1L;

  public VerificationInvalidException(String message, Object... params) {
    super(message, params);
  }
}
