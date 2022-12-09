package app.wooportal.server.core.error;

import java.util.Map;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.error.errorMessage.ErrorMessageService;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherResult;
import io.leangen.graphql.execution.InvocationContext;
import io.leangen.graphql.execution.ResolverInterceptor;

@Service
public class ExceptionResolverInterceptor implements ResolverInterceptor {
  
  private ErrorMessageService errorMessageService;
  
  public ExceptionResolverInterceptor(
      ErrorMessageService errorMessageService) {
    this.errorMessageService = errorMessageService;
  }

  @Override
  public Object aroundInvoke(InvocationContext context, Continuation continuation)
      throws Exception {
    try {
      return continuation.proceed(context);
    } catch (Exception e) {
      e.printStackTrace();
      return createErrorObject(context, e);
    }
  }
  
  public Object createErrorObject(
      InvocationContext context, 
      Exception e) {
    return DataFetcherResult.newResult()
        .error(GraphqlErrorBuilder
            .newError(context.getResolutionEnvironment().dataFetchingEnvironment)
            .message(errorMessageService.getLocalizedMessageByException(e))
            .extensions(Map.of(
                "exception", e.getClass().getSimpleName(),
                "originalMessage", e.getMessage() != null 
                  ? e.getMessage()
                  : ""))
            .build())
        .build();
  }
}
