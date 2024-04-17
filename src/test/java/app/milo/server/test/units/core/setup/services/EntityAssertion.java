package app.milo.server.test.units.core.setup.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.core.utils.ReflectionUtils;

public class EntityAssertion {
  
  public static void assertEntityMatch(Object source, Object result) {
    assertThat(source).isNotNull();
    assertThat(result).isNotNull();
    assertThat(source).isOfAnyClassIn(result.getClass());
    
    assertThat(matched(source, result, null, null)).isTrue();
  }
  
  public static void assertEntityMatch(Object source, Object result, JsonNode context) {
    assertThat(source).isNotNull();
    assertThat(result).isNotNull();
    assertThat(source).isOfAnyClassIn(result.getClass());
    
    assertThat(matched(source, result, context, null)).isTrue();
  }
  
  private static boolean matched(
      Object source, 
      Object result,
      JsonNode context,
      Set<Object> checked) {
    if (checked == null) {
      checked = new HashSet<Object>();
    }
    
    if (!checked.contains(source)) {
      checked.add(source);
      for (var field : getFields(source)) {
        JsonNode node = null;
        if (context != null) {
          node = context.get(field.getName());
        }
        
        if (context == null || node != null) {
          var sourceValue = ReflectionUtils.get(field.getName(), source);
          var resultValue = ReflectionUtils.get(field.getName(), result);

          if (!matchField(sourceValue, resultValue, node, checked)) {
            return false;
          }
        }
      }
    }

    return true;
  }

  private static boolean matchField(
      Optional<Object> sourceValue, 
      Optional<Object> resultValue,
      JsonNode node,
      Set<Object> checked) {
    if (sourceValue.isEmpty() || resultValue.isEmpty()) {
      return sourceValue.isEmpty() && resultValue.isEmpty();
    }
    
    if (sourceValue.get() instanceof Collection<?>) {
      return matchCollection(
          sourceValue.get(), resultValue.get(), node, checked);
    }
    
    if (ReflectionUtils.isBaseType(sourceValue.get().getClass())) {
      return sourceValue.get().equals(resultValue.get());
    }
    
    return matched(
        sourceValue.get(), resultValue.get(), node, checked);
  }

  private static boolean matchCollection(
      Object sourceCollection,
      Object resultCollection,
      JsonNode node,
      Set<Object> checked) {
    for (var sourceChild : (Collection<?>) sourceCollection) {
      for (var resultChild : (Collection<?>) resultCollection) {
        if (matched(sourceChild, resultChild, node, checked)) {
          return true;
        }
      }
    }
    return false;
  }

  private static List<Field> getFields(Object source) {
    var fields = new ArrayList<Field>();
    fields.addAll(List.of(source.getClass().getDeclaredFields()));
    fields.addAll(List.of(source.getClass().getSuperclass().getDeclaredFields()));
    return fields;
  }

}
