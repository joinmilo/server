package app.milo.server.core.error.exception;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class BaseException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  protected Object[] params;

  public BaseException(String message, Object... params) {
    super(String.format("%1$s, params: %2$s", 
        message,
        params != null
          ? Stream.of(params).map(p -> p != null ? p.toString() : "").collect(Collectors.joining(", "))
          : ""));
    this.params = params;
  }
}
