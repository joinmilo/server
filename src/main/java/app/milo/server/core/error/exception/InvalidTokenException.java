package app.milo.server.core.error.exception;

public class InvalidTokenException extends BaseException {

  private static final long serialVersionUID = 1L;
  
  public InvalidTokenException(String message, Object... params) {
    super(message, params);
  }
}
