package app.milo.server.core.error.exception;

public class InvalidCaptchaException extends BaseException {

  private static final long serialVersionUID = 1L;
  
  public InvalidCaptchaException(String message, Object... params) {
    super(message, params);
  }
}
