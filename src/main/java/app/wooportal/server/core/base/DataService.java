package app.wooportal.server.core.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityGraph;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.querydsl.core.types.Predicate;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.config.SetNullOnRemoval;
import app.wooportal.server.core.context.ApiContextAdapter;
import app.wooportal.server.core.error.exception.DuplicateException;
import app.wooportal.server.core.error.exception.NotNullableException;
import app.wooportal.server.core.repository.BaseRepositoryQuery;
import app.wooportal.server.core.repository.CollectionRepositoryQuery;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.PersistenceUtils;
import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.core.utils.SortPageUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Transactional
public abstract class DataService<E extends BaseEntity, P extends PredicateBuilder<?, E>> {

  protected DataRepository<E> repo;

  protected P predicate;

  protected Map<String, DataService<? extends BaseEntity, ? extends PredicateBuilder<?, ?>>> fieldToServices;
  protected Map<String, DataService<? extends BaseEntity, ? extends PredicateBuilder<?, ?>>> classToServices;

  @Autowired
  protected ApiContextAdapter context;
  
  @Autowired
  protected GraphBuilder<E> graph;
  
  @Autowired
  protected ObjectMapper objectMapper;

  public DataService(DataRepository<E> repo, P predicate) {
    this.repo = repo;
    this.predicate = predicate;
    fieldToServices = new HashMap<>();
    classToServices = new HashMap<>();
  }

  @SuppressWarnings("hiding")
  protected <E extends BaseEntity, B extends PredicateBuilder<?, E>> void addService(String field,
      DataService<E, B> service) throws IllegalArgumentException {
    if (fieldToServices.containsKey(field)) {
      throw new IllegalArgumentException("Field: " + field + " already exists with service: "
          + fieldToServices.get(field).getClass().getSimpleName());
    }   
    
    if (ReflectionUtils.getField(getEntityClass(), field).isEmpty()) {
      throw new IllegalArgumentException("Field: " + field + " does not exist in entity: " + getEntityClass());
    }
    
    classToServices.put(ClassUtils.getUserClass(service.getClass()).getSimpleName(), service);
    fieldToServices.put(field, service);
  }
  
  @SuppressWarnings("unchecked")
  protected Class<E> getEntityClass() {
    return (Class<E>) ReflectionUtils.getGenericTypes(getClass()).get(0);
  }

  @SuppressWarnings("unchecked")
  protected DataService<E, P> getService(String field) {
    return (DataService<E, P>) fieldToServices.get(field);
  }
  
  @SuppressWarnings("unchecked")
  protected <D extends DataService<?, ?>> D getService(Class<D> serviceClass) {
    return (D) classToServices.get(ClassUtils.getUserClass(serviceClass).getSimpleName());
  }
  
  public Optional<E> readOne(BaseRepositoryQuery<E> query) {
    return repo.findOne(query);
  }

  public PageableList<E> readAll() {
    return new PageableList<E>(repo.findAll());
  }

  public PageableList<E> readAll(FilterSortPaginate params) {
    var query = collectionQuery(true).addGraph(graph.create(getEntityClass(), context.getMultiReadContext()));
    
    if (params != null) {
      if ((params.getSearch() != null && !params.getSearch().isBlank())
          || params.getExpression() != null) {
        query.and(predicate.create(params));
      }
      
      if (params.getPage() != null || params.getSize() != null) {
        query.setPage(SortPageUtils.createPage(params));
      } else if (params.getSort() != null) {
        query.setSort(SortPageUtils.createSort(params));
      }
    }
   
    return readAll(query);
  }

  public PageableList<E> readAll(CollectionRepositoryQuery<E> query) {
    return repo.findAll(query);
  }

  public Optional<E> getExisting(E entity) {
    return entity != null && entity.getId() != null
       ? repo.findOne(singleQuery(predicate.withId(entity.getId())))
       : Optional.empty();
  }
  
  public Optional<E> getByExampleWithContext(E entity) {
    return getByExample(entity, context.getSingleReadContext());
  }

  public Optional<E> getByExample(E entity) {
    return getByExample(entity, null);
  }
  
  public Optional<E> getByExample(E entity, List<graphql.language.Field> context) {
    if (entity != null) {
      var query = collectionQuery(predicate.withExample(entity));
      
      if (context != null && !context.isEmpty()) {
        query.addGraph(graph.create(getEntityClass(), context));
      }
      
      var result = repo.findAll(query);
      return Optional.ofNullable(result != null && !result.isEmpty() ? result.get(0) : null);
    }
    return Optional.empty();
  }
  
  public BaseRepositoryQuery<E> singleQuery(Predicate predicate) {
    return BaseRepositoryQuery
        .init(getEntityClass(), graph)
        .and(predicate);
  }
  
  public CollectionRepositoryQuery<E> collectionQuery(Predicate predicate) {
    return collectionQuery(false, predicate);
  }

  public CollectionRepositoryQuery<E> collectionQuery(
      boolean defaultSort, Predicate predicate) {
    return collectionQuery(defaultSort)
        .and(predicate);
  }
  
  public CollectionRepositoryQuery<E> collectionQuery(
      boolean defaultSort) {
    return CollectionRepositoryQuery.init(getEntityClass(), graph, defaultSort);
  }
  
  public EntityGraph<E> graph(String... graphDef) {
    return graph.create(getEntityClass(), graphDef);
  }
  
  protected JsonNode createContext(String... fields) {
    var context = objectMapper.createObjectNode();
    for (var field : fields) {
      setContext(field, context);
    }
    return context;
  }
  
  protected JsonNode setContext(String field, JsonNode context) {
    if (context != null && (context.get(field) == null || context.get(field).isNull())
        && context instanceof ObjectNode) {
      ((ObjectNode) context).set(field, null);
    }
    return context;
  }
  
  protected JsonNode removeContext(String field, JsonNode context) {
    if (context != null && context.has(field)
        && context instanceof ObjectNode) {
      ((ObjectNode) context).remove(field);
    }
    return context;
  }
  
  public List<E> saveAll(Collection<E> entities) {
    return entities.stream().map(this::save).collect(Collectors.toList());
  }

  public E saveWithContext(E newEntity) {
    if (newEntity != null) {
      return save(newEntity, context.getSingleSaveContext());
    }
    return newEntity;
  }
  
  public E save(E newEntity) {
    return save(newEntity, null);
  }

  public E save(E newEntity, JsonNode context) {
    if (newEntity != null) {
      return getExistingWithId(newEntity) 
          .map(entity -> persist(entity, newEntity, context))
          .orElseGet(() -> persist(ReflectionUtils.newInstance(newEntity), newEntity, context));
    }
    return newEntity;
  }

  @SuppressWarnings("unchecked")
  public E persist(E entity, E newEntity, JsonNode context) {
    entity = (E) Hibernate.unproxy(entity);
    newEntity = (E) Hibernate.unproxy(newEntity);
    var copy = createCopy(entity);
    
    performPreSave(entity, newEntity, context);
    
    validate(entity, newEntity);
    
    var postFieldNames = saveFields(entity, newEntity, context);
    var saved = persist(entity);
    savePostSaveFields(saved, newEntity, postFieldNames, context);
    
    performPostSave(copy, saved, context);
    return saved;
  }

  private E createCopy(E entity) {
    var existing = getExistingWithId(entity);
    
    return existing.isPresent()
        ? existing.get()
        : ReflectionUtils.newInstance(entity);
  }

  private Optional<E> getExistingWithId(E newEntity) {
    return repo.findOne(singleQuery(predicate.withId(newEntity.getId())))
        .map(Optional::of)
        .orElseGet(() -> getExisting(newEntity));
  }
  
  private void performPreSave(E entity, E newEntity, JsonNode context) {
    if (entity.getId() != null) {
      preUpdate(entity, newEntity, context);
    } else {
      preCreate(entity, newEntity, context);
    }
    preSave(entity, newEntity, context);
  }
  
  protected void preUpdate(E entity, E newEntity, JsonNode context) {}
  
  protected void preCreate(E entity, E newEntity, JsonNode context) {}

  protected void preSave(E entity, E newEntity, JsonNode context) {}

  public void validate(E entity, E newEntity) {
    var uniqueFields = new HashMap<String, Object>();
    for (var field: ReflectionUtils.getFields(entity.getClass())) {
      if (!PersistenceUtils.isIgnoredField(field)) {
        checkNullable(entity, newEntity, field);
        
        var value = ReflectionUtils.get(field.getName(), newEntity);
        if (PersistenceUtils.isUniqueConstraint(field)
            && value.isPresent()) {
          uniqueFields.put(field.getName(), value.get());
        }
      }
    }
    checkDuplicates(uniqueFields, entity);
  }

  private void checkNullable(E entity, E newEntity, Field field) {
    if (!PersistenceUtils.isNullable(entity, field)
        && ReflectionUtils.get(field.getName(), entity).isEmpty()
        && ReflectionUtils.get(field.getName(), newEntity).isEmpty()) {
      throw new NotNullableException("Field not nullable is null", field.getName());
    }
  }
  
  @SuppressWarnings("unchecked")
  //TODO: Check duplicates also for class constraints
  private void checkDuplicates(Map<String, Object> uniqueFields, E entity) {
    if (uniqueFields != null && !uniqueFields.isEmpty()) {
      uniqueFields.forEach((fieldName, fieldValue) -> {
        var result = getByExample((E) ReflectionUtils.newInstance(getEntityClass())
            .set(fieldName, fieldValue));
        if (result.isPresent() && !result.get().getId().equals(entity.getId())) {
          throw new DuplicateException("Entity already exists", fieldName);
        }
      });
    }
  }
  
  private E persist(E entity) {
    var id = entity.getId();
    if (id == null || id.isEmpty() || id.isBlank()) {
      entity.setId(UUID.randomUUID().toString());
    }
    return repo.save(entity);
  }
  
  private void performPostSave(E entity, E saved, JsonNode context) {
    if (entity.getId() != null) {
      postUpdate(entity, saved, context);
    } else {
      postCreate(entity, saved, context);
    }
    postSave(entity, saved, context);
  }
  
  protected void postUpdate(E entity, E saved, JsonNode context) { }
  
  protected void postCreate(E entity, E saved, JsonNode context) { }
  
  protected void postSave(E entity, E saved, JsonNode context) { }

  protected List<String> saveFields(E entity, E newEntity, JsonNode context) {    
    return context != null && !context.isNull()
      ? saveFieldsWithContext(entity, newEntity, context)
      : saveFieldsWithoutContext(entity, newEntity);
  }

  protected List<String> saveFieldsWithContext(E entity, E newEntity, JsonNode context) {
    List<String> postSaveFields = new ArrayList<>();
    context.fields().forEachRemaining(node -> {
      Optional<String> listField = saveField(entity, newEntity, node.getKey(), node.getValue());
      listField.ifPresent(postSaveFields::add);
    });
    return postSaveFields;
  }
  
  protected List<String> saveFieldsWithoutContext(E entity, E newEntity) {
    List<String> postSaveFields = new ArrayList<>();
    for (Field field : ReflectionUtils.getFields(getEntityClass())) {
      Optional<String> listField = saveField(entity, newEntity, field.getName(), null);
      listField.ifPresent(postSaveFields::add);
    }
    return postSaveFields;
  }

  @SuppressWarnings("unchecked")
  protected Optional<String> saveField(E entity, E newEntity, String fieldName, JsonNode context) {
    if (ReflectionUtils.getAnnotation(entity, fieldName, OneToMany.class).isPresent()
        || PersistenceUtils.mappedBy(entity, fieldName, OneToOne.class).isPresent()) {
      return Optional.of(fieldName);
    }
    
    if (!PersistenceUtils.isIgnoredField(fieldName)) {
      var fieldValue = ReflectionUtils.get(fieldName, newEntity).orElse(null);
      if (ReflectionUtils.getAnnotation(entity, fieldName, ManyToMany.class).isPresent()
          && getService(fieldName) != null) {
        var currentValue = ReflectionUtils.get(fieldName, entity);
        if (currentValue.isPresent()) {
          getService(fieldName).deleteAll((Collection<E>) currentValue.get());
        }
        fieldValue = saveManyToMany((Collection<E>) fieldValue, getService(fieldName), context);
      }
      if (!fieldName.equals("id")
          && (fieldValue != null || PersistenceUtils.isNullable(entity, fieldName))) {        
        Object value = getService(fieldName) == null || fieldValue == null || Collection.class.isAssignableFrom(fieldValue.getClass())
            ? fieldValue instanceof String ? ((String) fieldValue).strip() : fieldValue
            : getService(fieldName).save((E) fieldValue, context);
        ReflectionUtils.set(fieldName, entity, value);
      }
    }
    return Optional.empty();
  }

  private List<E> saveManyToMany(
      Collection<E> fieldValue,
      DataService<E, P> service,
      JsonNode context) {
    if (fieldValue != null && !fieldValue.isEmpty()) {
      var result = new ArrayList<E>();
      for (E element : fieldValue) {
        result.add(service.save(element, findContext(element, context)));
      }
      return result;
    }
    return null;
  }

  protected void savePostSaveFields(E persisted, E newEntity, List<String> postSaveFieldNames, JsonNode context) {
    for (String postSaveFieldName : postSaveFieldNames) {
      if (getService(postSaveFieldName) != null) {
        JsonNode saveContext = context != null ? context.findValue(postSaveFieldName) : null;
        if (ReflectionUtils
            .getAnnotation(persisted, postSaveFieldName, OneToMany.class)
            .isPresent()) {
          saveOneToManyField(persisted, newEntity, postSaveFieldName, saveContext); 
        }
        
        if (ReflectionUtils
            .getAnnotation(persisted, postSaveFieldName, OneToOne.class)
            .isPresent()) {
          saveOneToOneField(persisted, newEntity, postSaveFieldName, saveContext);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  protected void saveOneToManyField(E persisted, E newEntity, String fieldName, JsonNode context) {
    var persistedSubEntities = ReflectionUtils.get(fieldName, persisted);
    var newSubEntities = ReflectionUtils.get(fieldName, newEntity);
    var owningField = PersistenceUtils.mappedBy(persisted, fieldName);
    
    if (newSubEntities.isPresent()
        && !((Collection<E>) newSubEntities.get()).isEmpty()
        && owningField.isPresent()) {
      List<E> result = new ArrayList<>();
      for (E newSub : (Collection<E>) newSubEntities.get()) {
        ReflectionUtils.set(owningField.get(), newSub, persisted);
        setParentToContext(context, owningField.get(), persisted);
        result.add(getService(fieldName).save(newSub, findContext(newSub, context)));
      }
          
      ReflectionUtils.set(
          fieldName, 
          persisted,
          ReflectionUtils.isFieldTypeOfObject(persisted, fieldName, Set.class)
            ? new HashSet<>(result) : result
      ); 
    } 
    
    if (persistedSubEntities.isPresent()
        && persistedSubEntities.get() != null 
        && !((Collection<E>) persistedSubEntities.get()).isEmpty()
        && owningField.isPresent()) {
      for (E persistedSub : (Collection<E>) persistedSubEntities.get()) {
        if (newSubEntities.isEmpty() || find((Collection<E>) newSubEntities.get(), persistedSub).isEmpty()) {
          if (ReflectionUtils
              .getAnnotation(persisted, fieldName, SetNullOnRemoval.class)
              .isPresent()) {
          ReflectionUtils.set(owningField.get(), persistedSub, null);
          getService(fieldName).persist(persistedSub, persistedSub, null);
          } else {
            getService(fieldName).deleteById(persistedSub.getId());
          }
        }
      } 
    }
  }
  
  protected Optional<Object> find(Collection<E> collection, E element) {
    if (collection != null && !collection.isEmpty() && element != null) {
      for (E collectionElement : collection) {
        if (isEqual(collectionElement, element)) {
          return Optional.of(collectionElement);
        }
      } 
    }
    return Optional.empty();
  }

  protected boolean isEqual(E nodeEntity, E entity) {
    if (PersistenceUtils.idsEqual(nodeEntity, entity)) {
      return true;
    }
    
    List<String> classUniqueConstraints = PersistenceUtils.getClassUniqueConstraintFields(nodeEntity);
    int columnEquals = 0;
    for (Field field : ReflectionUtils.getFields(entity.getClass())) {
      if (PersistenceUtils.uniqueColumnEqual(field, nodeEntity, entity)) {
        return true;
      }
      if (PersistenceUtils.uniqueClassConstraintEqual(classUniqueConstraints, field, nodeEntity, entity)) {
        columnEquals++;
      }
    }
    return classUniqueConstraints != null 
          && !classUniqueConstraints.isEmpty() 
          && columnEquals == classUniqueConstraints.size();
  }
  
  @SuppressWarnings("unchecked")
  protected JsonNode findContext(E entity, JsonNode context) {
    if (context != null) {
      for (JsonNode node : context) {
        try {
          if (isEqual(
              (E) objectMapper.treeToValue(node, entity.getClass()),
              entity)) {
            return node;
          }
        } catch (JsonProcessingException ignored) { }
      }
    }
    return null;
  }
  
  @SuppressWarnings("unchecked")
  protected void saveOneToOneField(
      E persisted, E newEntity, String fieldName, JsonNode context) {
    var owningField = PersistenceUtils.mappedBy(persisted, fieldName);
    var subEntity = ReflectionUtils.get(fieldName, newEntity);
    if (subEntity.isPresent() && owningField.isPresent()) {
      ReflectionUtils.set(owningField.get(), subEntity.get(), persisted);
      setParentToContext(context, owningField.get(), persisted);
      ReflectionUtils.set(
          fieldName, 
          persisted, 
          getService(fieldName).save((E) subEntity.get(), context));
    }

  }
  
  protected void setParentToContext(
      JsonNode context,
      String fieldName, 
      E value) {
    if (context != null && fieldName != null && !fieldName.isBlank()) {
      ObjectNode parentNode = objectMapper.createObjectNode();
      parentNode.put("id", value.getId());
      
      if (context instanceof ArrayNode) {
        context.forEach(node -> ((ObjectNode) node).set(fieldName, parentNode));
      }
      
      if (context instanceof ObjectNode) {
        ((ObjectNode) context).set(fieldName, parentNode);
      }
    }
  }
  
  // TODO: Instead of querying objects and pass result to delete method (which in turn calls another
  // query), delete by query 
  public void deleteAll(CollectionRepositoryQuery<E> query) {
    deleteAll(repo.findAll(query).getList());
  }

  public void deleteAll(Collection<E> entities) {
    if (entities != null && !entities.isEmpty()) {
      entities.forEach(entity -> {
        if (entity != null && entity.getId() != null && !entity.getId().isBlank()) {
          deleteById(entity.getId());
        }
      });
    }
  }

  public void deleteById(String... ids) {
    if (ids != null) {
      for (String id : ids) {
        if (id != null && !id.isBlank()) {
          repo.deleteById(id);
        }
      }
    }
  }

}
