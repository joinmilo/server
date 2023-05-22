package app.wooportal.server.core.context;

import java.util.HashMap;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class DataServiceContext {
  
  private final ApplicationContext applicationContext;
  
  private Map<Class<? extends BaseEntity>, DataService<?, ?>> dataServices;
  
  public DataServiceContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @SuppressWarnings("unchecked")
  @EventListener(ApplicationReadyEvent.class)
  private void mapServicesToEntity() {
    this.dataServices = new HashMap<>();
    for (var beanName : applicationContext.getBeanDefinitionNames()) {
      var bean = getBeanWithoutProxy(beanName);
      
      if (DataService.class.isAssignableFrom(bean.getClass())) {
        for (var type : ReflectionUtils.getGenericTypes(bean.getClass())) {
          if (BaseEntity.class.isAssignableFrom(type.getClass())) {
            dataServices.put((Class<? extends BaseEntity>) type.getClass(), (DataService<?, ?>) bean);
            continue;
          }
        }
      }
    }
  }
  
  protected Object getBeanWithoutProxy(String beanName) {
    var bean = applicationContext.getBean(beanName);
    if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
        try {
            return ((Advised) bean).getTargetSource().getTarget();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    return bean;
}
  
  public <E extends BaseEntity> DataService<?, ?> getService(Class<E> entityClass) {
    return this.dataServices.get(entityClass);
  }

}
