package app.wooportal.server.core.error.exception;

public class DuplicateException extends BaseException {

  private static final long serialVersionUID = 1L;

  public DuplicateException(String message, Object... params) {
    super(message, params);
  }
}
