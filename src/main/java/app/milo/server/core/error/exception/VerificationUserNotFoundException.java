package app.milo.server.core.error.exception;

public class VerificationUserNotFoundException extends BaseException {

  private static final long serialVersionUID = 1L;

  public VerificationUserNotFoundException(String message, Object... params) {
    super(message, params);
  }
}
