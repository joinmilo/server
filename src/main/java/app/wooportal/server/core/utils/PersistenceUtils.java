package app.wooportal.server.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.Hibernate;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;

public class PersistenceUtils {

  public static boolean isValidField(Field field) {
    return field != null
        && !isIgnoredField(field.getName())
        && (ReflectionUtils.getAnnotation(field, Transient.class).isEmpty()
            || ReflectionUtils.getAnnotation(field, Translatable.class).isPresent());
  }
  
  public static boolean isIgnoredField(Field field) {
    return isIgnoredField(field.getName());
  }

  public static boolean isIgnoredField(String fieldName) {
    return fieldName.equals("serialVersionUID");
  }

  public static boolean isValidEntity(Object obj) {
    return obj != null && BaseEntity.class.isAssignableFrom(obj.getClass());
  }

  public static boolean isValidCollection(Object obj) {
    return obj != null && AbstractCollection.class.isAssignableFrom(obj.getClass());
  }

  public static boolean isNullable(Object obj, String fieldName) {
    if (obj == null || fieldName == null) {
      return true;
    }
    var field = ReflectionUtils.getField(obj.getClass(), fieldName);
    return field.isEmpty()
        || isNullable(obj, field.get());
  }
  
  public static boolean isNullable(Object obj, Field field) {
    return obj == null
        || field == null
        || (isColumnNullable(field)
            && isJoinColumnNullable(field)
            && !field.getName().equals("id"));
  }

  private static boolean isColumnNullable(Field field) {
    var columnAnnotation = field.getDeclaredAnnotation(Column.class);
    return columnAnnotation == null || columnAnnotation.nullable();
  }

  private static boolean isJoinColumnNullable(Field field) {
    var columnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
    return columnAnnotation == null || columnAnnotation.nullable();
  }

  public static <E extends BaseEntity> boolean idsEqual(E source, E target) {
    if (source == null || target == null) {
      throw new IllegalArgumentException("params must not be null");
    }

    var sourceId = ReflectionUtils.get("id", source);
    var targetId = ReflectionUtils.get("id", target);

    return sourceId.isPresent() && targetId.isPresent() && sourceId.equals(targetId);
  }

  public static List<String> getClassUniqueConstraintFields(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException("object must not be null");
    }

    var tableAnnotation = obj.getClass().getDeclaredAnnotation(Table.class);
    if (tableAnnotation != null) {
      var uniqueConstraints = tableAnnotation.uniqueConstraints();
      var constraintFields = new ArrayList<String>();
      for (UniqueConstraint uniqueConstraint : uniqueConstraints) {
        constraintFields.addAll(Arrays.asList(uniqueConstraint.columnNames()));
      }
      return constraintFields;
    }
    return null;
  }

  public static boolean uniqueColumnEqual(Field field, Object source, Object target) {
    if (field == null || source == null || target == null) {
      throw new IllegalArgumentException("params must not be null");
    }

    if(isUniqueConstraint(field)) {
      var fieldValueEntity = ReflectionUtils.get(field.getName(), target);
      var fieldValueNode = ReflectionUtils.get(field.getName(), source);
      return fieldValueEntity.isPresent()
          && fieldValueNode.isPresent()
          && fieldValueEntity.equals(fieldValueNode);
    }
    return false;
  }
  
  public static boolean isUniqueConstraint(Field field) {
    var columnAnnotation = field.getDeclaredAnnotation(Column.class);
    var joinColumnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
    return columnAnnotation != null && columnAnnotation.unique()
        || joinColumnAnnotation != null && joinColumnAnnotation.unique();
  }

  public static boolean uniqueClassConstraintEqual(
      List<String> constraints, Field field, Object source, Object target) {
    if (field == null || source == null || target == null) {
      throw new IllegalArgumentException("params must not be null");
    }

    if (constraints != null
        && !constraints.isEmpty()
        && isClassUniqueConstraint(field, constraints)) {
      var entityValue =
          (Optional<?>) Hibernate.unproxy(ReflectionUtils.get(field.getName(), target));
      var nodeValue = (Optional<?>) Hibernate.unproxy(ReflectionUtils.get(field.getName(), source));
      return ( entityValue.isPresent() && nodeValue.isPresent() 
              && (entityValue.get().equals(nodeValue.get()) || entityValue.get() == nodeValue.get())
            ) || (entityValue.isEmpty() && nodeValue.isEmpty());
    }
    return false;
  }

  private static boolean isClassUniqueConstraint(Field field, List<String> classUniqueConstraints) {
    JoinColumn columnAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
    if (columnAnnotation != null) {
      return columnAnnotation.name().isEmpty()
          ? isJoinColumnConstraintHeuristically(field.getName(), classUniqueConstraints)
          : classUniqueConstraints.contains(columnAnnotation.name());
    }

    return classUniqueConstraints.contains(field.getName());
  }

  private static boolean isJoinColumnConstraintHeuristically(
      String fieldName, List<String> classUniqueConstraints) {
    var sb = new StringBuilder();
    for (int i = 0; i < fieldName.length(); i++) {
      char c = fieldName.charAt(i);
      if (Character.isUpperCase(c)) {
        sb.append("_");
        sb.append(Character.toLowerCase(c));
      } else {
        sb.append(c);
      }
    }
    sb.append("_id");

    return classUniqueConstraints.contains(sb.toString());
  }

  @SuppressWarnings("unchecked")
  @SafeVarargs
  public static <A extends Annotation> Optional<String> mappedBy(
      Object obj, String fieldName, Class<A>... annotations) {
    if (obj == null || fieldName == null || fieldName.isBlank()) {
      throw new IllegalArgumentException("params must not be null");
    }

    if (annotations.length == 0) {
      annotations = (Class<A>[]) new Class<?>[] {OneToOne.class, OneToMany.class};
    }

    for (Class<A> annotation : annotations) {
      var value = ReflectionUtils.getAnnotationValue(obj, fieldName, annotation, "mappedBy");

      if (value.isPresent()) {
        return value.map(v -> ((String) v).isBlank() ? null : (String) v);
      }
    }
    return Optional.empty();
  }
}
