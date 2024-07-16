package app.milo.server.core.error;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;
import app.milo.server.core.error.errorMessage.ErrorMessageService;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;

@Service
public class CustomExceptionHandler implements DataFetcherExceptionHandler {

  private final ErrorMessageService errorMessageService;

  public CustomExceptionHandler(ErrorMessageService errorMessageService) {
      this.errorMessageService = errorMessageService;
  }

  @Override
  public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(
      DataFetcherExceptionHandlerParameters handlerParameters) {
    var exception = handlerParameters.getException();
    var localizedMessage = errorMessageService.getLocalizedMessageByException(exception);
    var path = handlerParameters.getPath();
    var sourceLocation = handlerParameters.getSourceLocation();
    
    exception.printStackTrace();

    var errorBuilder = GraphqlErrorBuilder.newError()
            .message(localizedMessage)
            .path(path)
            .location(sourceLocation)
            .extensions(Map.of(
                    "exception", exception.getClass().getSimpleName(),
                    "originalMessage", exception.getMessage() != null ? exception.getMessage() : ""));

    return CompletableFuture.completedFuture(DataFetcherExceptionHandlerResult.newResult()
            .error(errorBuilder.build())
            .build());
  } 
 
}