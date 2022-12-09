package app.wooportal.server.core.error.exception;

public class InvalidVerificationException extends BaseException {

  private static final long serialVersionUID = 1L;

  public InvalidVerificationException(String message, Object... params) {
    super(message, params);
  }
}
