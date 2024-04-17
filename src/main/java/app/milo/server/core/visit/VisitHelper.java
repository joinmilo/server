package app.milo.server.core.visit;

import java.lang.reflect.Field;
import java.util.Optional;
import app.milo.server.core.utils.ReflectionUtils;
import app.milo.server.core.visit.visitable.VisitableEntity;

public class VisitHelper {
  
  public static Optional<Class<VisitableEntity<?>>> getVisitableType(Object entity) {
    for (Field field : entity.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      var visitorType = getVisitorType(field);
      if (visitorType.isPresent()) {
        return visitorType;
      }
    }
    return Optional.empty();
  }

  @SuppressWarnings("unchecked")
  private static Optional<Class<VisitableEntity<?>>> getVisitorType(Field fieldType) {
    var genericType = ReflectionUtils.getGenericFieldType(fieldType);
    if (genericType.isPresent() 
        && VisitableEntity.class.isAssignableFrom(genericType.get())) {
      return Optional.of((Class<VisitableEntity<?>>) genericType.get());
    }
    return Optional.empty();
  }  
  
}
