package app.wooportal.server.core.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextAdapter implements ApplicationContextAware {

  private static ApplicationContext context;

  public static <T> T bean(Class<T> beanType) {
    return context.getBean(beanType);
  }

  public static Object bean(String name) {
    return context.getBean(name);
  }

  @Override
  public void setApplicationContext(ApplicationContext ac) {
    context = ac;
  }
}
