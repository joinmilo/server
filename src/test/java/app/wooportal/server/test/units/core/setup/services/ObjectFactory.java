package app.wooportal.server.test.units.core.setup.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.test.units.core.setup.entities.base.TestEntity;
import app.wooportal.server.test.units.core.setup.entities.child.TestChildEntity;
import app.wooportal.server.test.units.core.setup.entities.listChild.TestListChildEntity;

public class ObjectFactory {
  
  public static TestEntity newTestEntity(Map<String, Object> values) {
    return newInstance(TestEntity.class, values);
  }
  
  public static TestChildEntity newTestChildEntity(Map<String, Object> values) {
    return newInstance(TestChildEntity.class, values);
  }
  
  public static TestListChildEntity newTestListChildEntity(Map<String, Object> values) {
    return newInstance(TestListChildEntity.class, values);
  }
  
  public static <T> T newInstance(Class<T> clazz) {
    return newInstance(clazz, null);
  }
  
  public static <T> T newInstance(Class<T> clazz, Map<String, Object> values) {
    try {
      var obj = clazz.getDeclaredConstructor().newInstance();
      
      if (values != null && !values.isEmpty()) {
        for (Entry<String, Object> entry : values.entrySet()) {
          ReflectionUtils.set(entry.getKey(), obj, entry.getValue());
        }
      }
      return (T) obj;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
    }
    return null;
  }

}
