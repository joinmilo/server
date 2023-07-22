package app.wooportal.server.core.error.exception;

public class ServiceUnavailableException extends BaseException {

  private static final long serialVersionUID = 1L;

  public ServiceUnavailableException(String message, Object... params) {
    super(message, params);
  }
 
}
