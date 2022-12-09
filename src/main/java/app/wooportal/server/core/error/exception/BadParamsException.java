package app.wooportal.server.core.error.exception;

public class BadParamsException extends BaseException {

  private static final long serialVersionUID = 1L;

  public BadParamsException(String message, Object... params) {
    super(message, params);
  }
 
}
