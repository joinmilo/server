package app.wooportal.server.core.i18n.translation;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;

public class TranslationUtils {
  
  @SuppressWarnings("unchecked")
  public static <T extends TranslatableEntity<?>> Optional<T> getTranslatables(Object object, String locale)
      throws IllegalArgumentException, IllegalAccessException {
    var field = getTranslatableField(object);
    
    if (field.isPresent()) {
      field.get().setAccessible(true);
      for (var translatable: (Collection<T>) field.get().get(object)) {
        if (translatable.getLanguage().getLocale().equals(locale)) {
          return Optional.ofNullable(translatable);
        }
      }
    }
    return Optional.empty();
  }

  public static Optional<Field> getTranslatableField(Object object) {
    return getTranslatableField(object.getClass());
  }
  
  public static Optional<Field> getTranslatableField(Class<?> objectClass) {
    for (var field : objectClass.getDeclaredFields()) {
      if (getTranslatableType(field.getGenericType()) != null) {
        return Optional.ofNullable(field);
      }
    }
    return Optional.empty();
  }
  
  @SuppressWarnings("unchecked")
  private static <T extends TranslatableEntity<?>> Class<T> getTranslatableType(Type fieldType) {
    if (fieldType instanceof ParameterizedType) {
      var pt = (ParameterizedType) fieldType;
      var genericType = (Class<?>) pt.getActualTypeArguments()[0];
      if (TranslatableEntity.class.isAssignableFrom(genericType)) {
        return (Class<T>) genericType;
      }
    }
    return null;
  }
}