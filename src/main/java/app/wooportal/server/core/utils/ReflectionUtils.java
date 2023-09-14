package app.wooportal.server.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ReflectionUtils {

  private static final Set<Class<?>> BASE_TYPES = getBaseTypes();
  
  public static <E> E copy(E object) {
    var newObject = newInstance(object);
    for (var field : getFields(object.getClass())) {
      var value = get(field.getName(), object);
      if (value.isPresent()
          && !Modifier.isFinal(field.getModifiers())
          && !Modifier.isStatic(field.getModifiers())) {
        set(
            field.getName(), 
            newObject, 
            value.get());
      }
    }
    return newObject;
  }

  public static <A extends Annotation> Optional<Object> getAnnotationValue(
      Object obj, String fieldName, Class<A> annotationClass, String annotationField) {
    if (obj == null
        || fieldName == null
        || fieldName.isBlank()
        || annotationClass == null
        || annotationField == null
        || annotationField.isBlank()) {
      throw new IllegalArgumentException("params must not be null or blank");
    }

    return getAnnotation(obj, fieldName, annotationClass)
        .map(
            annotation -> {
              try {
                return annotation
                    .annotationType()
                    .getDeclaredMethod(annotationField)
                    .invoke(annotation);
              } catch (IllegalAccessException
                  | IllegalArgumentException
                  | InvocationTargetException
                  | NoSuchMethodException
                  | SecurityException e) {
                return null;
              }
            });
  }

  public static <A extends Annotation> List<Field> getFieldsWithAnnotation(
      Class<?> sourceClass, Class<A> annotation) {
    if (sourceClass == null || annotation == null) {
      throw new IllegalArgumentException("params must not be null");
    }

    var result = new ArrayList<Field>();
    for (var field : getFields(sourceClass)) {
      var fieldWithAnnotation = getAnnotation(field, annotation);
      if (fieldWithAnnotation.isPresent()) {
        result.add(field);
      }
    }
    return result;
  }

  public static <A extends Annotation> Optional<A> getAnnotation(
      Object obj, String fieldName, Class<A> annotation) {
    if (obj == null || fieldName == null || fieldName.isBlank() || annotation == null) {
      throw new IllegalArgumentException("params must not be null or blank");
    }
    return getAnnotation(obj.getClass(), fieldName, annotation);
  }

  public static <A extends Annotation> Optional<A> getAnnotation(
      Class<?> sourceClass, String fieldName, Class<A> annotation) {
    if (sourceClass == null || fieldName == null || fieldName.isBlank() || annotation == null) {
      throw new IllegalArgumentException("params must not be null or blank");
    }

    try {
      return getAnnotation(sourceClass.getDeclaredField(fieldName), annotation);
    } catch (NoSuchFieldException | SecurityException e) {
      return Optional.empty();
    }
  }

  public static <A extends Annotation> Optional<A> getAnnotation(
      Field field, Class<A> annotationClass) {
    if (field == null || annotationClass == null) {
      throw new IllegalArgumentException("params must not be null");
    }

    return Optional.ofNullable(field.getDeclaredAnnotation(annotationClass));
  }

  public static Optional<Field> getField(Class<?> sourceClass, String fieldName) {
    if (sourceClass == null || fieldName == null || fieldName.isBlank()) {
      throw new IllegalArgumentException("params must not be null or blank");
    }

    for (var field : getFields(sourceClass)) {
      if (field.getName().equals(fieldName)) {
        return Optional.of(field);
      }
    }

    return Optional.empty();
  }

  public static boolean isFieldTypeOfObject(Object source, String fieldName, Class<?>... targets) {
    if (source == null || fieldName == null || fieldName.isBlank() || targets == null || targets.length < 1) {
      throw new IllegalArgumentException("params must not be null");
    }
    return isFieldTypeOfClass(source.getClass(), fieldName, targets);
  }

  public static boolean isFieldTypeOfClass(
      Class<?> sourceClass, String fieldName, Class<?>... targets) {
    if (sourceClass == null || fieldName == null || fieldName.isBlank() || targets == null || targets.length < 1) {
      throw new IllegalArgumentException("params must not be null");
    }

    Optional<Field> field = getField(sourceClass, fieldName);
    if (field.isPresent()) {
      for (Class<?> target : targets) {
        var type = field.get().getType();
        if (target.isAssignableFrom(type)) {
          return true;
        }
      }
    }
    return false;
  }

  public static List<Field> getFields(Class<?> sourceClass) {
    var fields = new ArrayList<Field>();

    if (sourceClass != null) {
      for (; sourceClass != null; sourceClass = sourceClass.getSuperclass()) {
        fields.addAll(Arrays.asList(sourceClass.getDeclaredFields()));
      }
    }

    return fields;
  }

  public static Optional<Class<?>> getFieldType(Class<?> sourceClass, String fieldName) {
    if (sourceClass == null || fieldName == null || fieldName.isBlank()) {
      throw new IllegalArgumentException("params must not be null");
    }

    return getField(sourceClass, fieldName).map(ReflectionUtils::getFieldType);
  }

  public static Class<?> getFieldType(Field field) {
    if (field == null) {
      throw new IllegalArgumentException("params must not be null");
    }

    return field.getType();
  }
  
  public static Optional<Class<?>> getGenericFieldType(Field field) {
    if (field.getGenericType() instanceof ParameterizedType) {
      var pt = (ParameterizedType) field.getGenericType();
      var genericType = (Class<?>) pt.getActualTypeArguments()[0];
      return Optional.ofNullable((Class<?>) genericType);
    }
    return Optional.empty();
  }

  public static Optional<Object> get(String fieldName, Object obj) {
    if (obj == null || fieldName == null || fieldName.isBlank()) {
      throw new IllegalArgumentException("params must not be null or blank");
    }

    for (var c = obj.getClass(); c != null; c = c.getSuperclass()) {
      try {
        return Optional.ofNullable(get(fieldName, c, obj));
      } catch (NoSuchFieldException | SecurityException | IllegalAccessException ignored) {
      }
    }
    return Optional.empty();
  }

  private static Object get(String fieldName, Class<?> sourceClass, Object obj)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    var field = sourceClass.getDeclaredField(fieldName);
    field.setAccessible(true);
    return field.get(obj);
  }

  public static void set(String fieldName, Object obj, Object value) {
    if (obj == null || fieldName == null || fieldName.isBlank()) {
      throw new IllegalArgumentException("params must not be null or blank");
    }

    try {
      var field = getField(obj.getClass(), fieldName);
      if (field.isEmpty()) {
        throw new NoSuchFieldException(fieldName);
      }
      field.get().setAccessible(true);
      field.get().set(obj, value);
    } catch (IllegalAccessException | NoSuchFieldException | SecurityException e) {
      e.printStackTrace();
      // TODO: Log errors
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T newInstance(T object) {
    if (isBaseType(object.getClass())) {
      return object;
    }

    T instance = (T) newInstance(object.getClass());
    return instance == null ? object : instance;
  }

  public static <T> T newInstance(Class<T> sourceClass) {
    if (sourceClass == null) {
      throw new IllegalArgumentException("class must not be null");
    }

    try {
      return sourceClass.getConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException
        | NoSuchMethodException
        | SecurityException e) {
      // TODO Log error
      e.printStackTrace();
    }
    return null;
  }

  public static List<Type> getGenericTypes(Class<?> sourceClass) {
    if (sourceClass != null) {
      var genericClass = sourceClass.getGenericSuperclass();
      if (genericClass instanceof ParameterizedType) {
        return new ArrayList<>(
            Arrays.asList(((ParameterizedType) genericClass).getActualTypeArguments()));
      }
    }
    return null;
  }

  public static boolean isBaseType(Class<?> sourceClass) {
    return BASE_TYPES.contains(sourceClass);
  }

  private static Set<Class<?>> getBaseTypes() {
    var types = new HashSet<Class<?>>();
    types.add(Boolean.class);
    types.add(Byte.class);
    types.add(Character.class);
    types.add(Double.class);
    types.add(Float.class);
    types.add(Integer.class);
    types.add(Long.class);
    types.add(Short.class);
    types.add(String.class);
    types.add(Void.class);
    return types;
  }
}
