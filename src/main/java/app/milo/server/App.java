package app.milo.server;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.milo.server.core.error.CustomExceptionHandler;
import app.milo.server.core.error.errorMessage.ErrorMessageService;
import app.milo.server.core.i18n.TranslationPreprocessor;
import app.milo.server.core.repository.CustomRepositoryFactoryBean;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.AsyncSerialExecutionStrategy;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.metadata.strategy.value.ValueMapperFactory;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapper;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import jakarta.annotation.PostConstruct;
import javassist.CannotCompileException;
import javassist.NotFoundException;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@EnableAsync
@EnableWebMvc
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class App {

  @Autowired
  private ObjectMapper mapper;
  
  @Autowired
  private ErrorMessageService errorMessageService;

  public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {
    TranslationPreprocessor.preprocess();
    SpringApplication.run(App.class, args);
  }

  @PostConstruct
  public void setTimeZone() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(name = "auditingDateTimeProvider")
  public DateTimeProvider dateTimeProvider() {
    return () -> Optional.of(OffsetDateTime.now(TimeZone.getDefault().toZoneId()));
  }

  @Bean
  public ValueMapperFactory<JacksonValueMapper> valueMapperFactory() {
    return (abstractTypes, environment) -> JacksonValueMapperFactory.builder().withPrototype(mapper)
        .build().getValueMapper(abstractTypes, environment);
  }

  @Bean
  public TomcatConnectorCustomizer asyncTimeoutCustomize() {
    return connector -> connector.setAsyncTimeout(180000);
  }
  
  @Bean
  public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
    var container = new ServletServerContainerFactoryBean();
    container.setMaxTextMessageBufferSize(32768);
    container.setMaxBinaryMessageBufferSize(32768);
    return container;
  }

  @Bean
  public GraphQL graphQL(GraphQLSchema schema)
  {
    return GraphQL.newGraphQL(schema)
      .queryExecutionStrategy(new AsyncExecutionStrategy(new CustomExceptionHandler(errorMessageService)))
      .mutationExecutionStrategy(new AsyncSerialExecutionStrategy(new CustomExceptionHandler(errorMessageService)))
      .build();
  }
}
