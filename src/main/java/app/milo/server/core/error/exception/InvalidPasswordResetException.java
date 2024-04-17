package app.milo.server.core.error.exception;

public class InvalidPasswordResetException extends BaseException {

  private static final long serialVersionUID = 1L;

  public InvalidPasswordResetException(String message, Object... params) {
    super(message, params);
  }
}
