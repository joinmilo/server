package app.wooportal.server.core.utils;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.config.DefaultSort;

public class SortPageUtils {

  public static final int PAGE_NUMBER_DEFAULT = 0;

  public static final int PAGE_SIZE_DEFAULT = 5;

  public static <E extends BaseEntity> Optional<Sort> createDefaultSort(Class<E> sourceClass) {
    if (sourceClass == null) {
      throw new IllegalArgumentException("sourceClass must not be null");
    }

    var defaultSortProp = getDefaultSortProperty(sourceClass, null);
    return defaultSortProp != null
        ? Optional.of(createSort(Sort.Direction.ASC, defaultSortProp))
        : Optional.empty();
  }

  @SuppressWarnings("unchecked")
  protected static <E extends BaseEntity> String getDefaultSortProperty(
      Class<E> sourceClass, String currentSort) {
    var defaultSortProp = getDefaultSort(sourceClass);

    if (defaultSortProp.isPresent()) {
      var sortField = defaultSortProp.get();

      currentSort =
          currentSort != null ? currentSort + "." + sortField.getName() : sortField.getName();

      var fieldType = ReflectionUtils.getFieldType(sortField);
      if (BaseEntity.class.isAssignableFrom(fieldType)) {
        var subField = sortField.getDeclaredAnnotation(DefaultSort.class).field();
        currentSort =
            subField.isBlank()
                ? getDefaultSortProperty((Class<E>) fieldType, currentSort)
                : getSubSortProperty((Class<E>) fieldType, subField, currentSort);
      }
    }
    return currentSort;
  }

  protected static <E extends BaseEntity> Optional<Field> getDefaultSort(Class<E> sourceClass) {
    var result = ReflectionUtils.getFieldsWithAnnotation(sourceClass, DefaultSort.class);

    if (!result.isEmpty() && result.size() > 1) {
      throw new AssertionError(
          "Annotation: " + DefaultSort.class.getName() + " can only be specified once");
    }

    return !result.isEmpty() ? Optional.of(result.get(0)) : Optional.empty();
  }

  @SuppressWarnings("unchecked")
  protected static <E extends BaseEntity> String getSubSortProperty(
      Class<E> sourceClass, String fieldName, String currentSort) {
    var fieldType = ReflectionUtils.getFieldType(sourceClass, fieldName);
    if (fieldType.isPresent()) {
      return BaseEntity.class.isAssignableFrom(fieldType.get())
          ? getDefaultSortProperty((Class<E>) fieldType.get(), currentSort + "." + fieldName)
          : currentSort + "." + fieldName;
    }
    return currentSort;
  }

  public static PageRequest createPage(FilterSortPaginate params) {
    if (params == null
        || (params.getPage() != null && params.getPage() < 0)
        || (params.getSize() != null && params.getSize() < 0)) {
      throw new IllegalArgumentException(
          MessageUtils.createMessage("Page params must not be null or negative", params));
    }

    var page = params.getPage() != null ? params.getPage() : PAGE_NUMBER_DEFAULT;
    var size = params.getSize() != null ? params.getSize() : PAGE_SIZE_DEFAULT;
    return params.getSort() == null || params.getSort().isBlank()
        ? PageRequest.of(page, size)
        : PageRequest.of(page, size, createSort(params));
  }

  public static Sort createSort(FilterSortPaginate params) {
    if (params == null || params.getSort() == null || params.getSort().isBlank()) {
      throw new IllegalArgumentException(
          MessageUtils.createMessage("Sort params must not be null or empty", params));
    }

    Direction direction = getDirection(params);

    return createSort(direction, params.getSort());
  }

  protected static Direction getDirection(FilterSortPaginate params) {
    return params.getDir() == null || isAsc(params) ? Sort.Direction.ASC : Sort.Direction.DESC;
  }

  protected static boolean isAsc(FilterSortPaginate params) {
    return params.getDir().trim().equalsIgnoreCase(Direction.ASC.toString());
  }

  public static Sort createSort(Direction dir, String... properties) {
    return createSort(true, dir, properties);
  }

  public static Sort createSort(boolean ignoreCase, Direction dir, String... properties) {
    return Sort.by(
        Stream.of(properties)
            .map(
                property ->
                    ignoreCase
                        ? new Sort.Order(dir, property).ignoreCase()
                        : new Sort.Order(dir, property))
            .collect(Collectors.toList()));
  }
}
