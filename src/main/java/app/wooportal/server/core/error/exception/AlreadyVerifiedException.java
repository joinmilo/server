package app.wooportal.server.core.error.exception;

public class AlreadyVerifiedException extends BaseException {

  private static final long serialVersionUID = 1L;

  public AlreadyVerifiedException(String message, Object... params) {
    super(message, params);
  }
  
}
