package app.wooportal.server.core.error.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import app.wooportal.server.core.error.exception.NotFoundException;

@ControllerAdvice
public class NotFoundAdvice {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> notFoundHandler(NotFoundException ex) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    return new ResponseEntity<String>("Not Found" + ex.getMessage(), status);
  }
}
