package app.wooportal.server.core.error.exception;

public class NotDeletableException extends BaseException {

  private static final long serialVersionUID = 1L;

  public NotDeletableException(String message, Object... params) {
    super(message, params);
  }
  
}
