package app.milo.server.core.error.exception;

public class NotFoundException extends BaseException {

  private static final long serialVersionUID = 1L;

  public NotFoundException(String message, Object... params) {
    super(message, params);
  }

}