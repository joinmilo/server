package app.milo.server.core.error.exception;

public class NotNullableException extends BaseException {

  private static final long serialVersionUID = 1L;

  public NotNullableException(String message, Object... fields) {
    super(message, fields);
  }

}