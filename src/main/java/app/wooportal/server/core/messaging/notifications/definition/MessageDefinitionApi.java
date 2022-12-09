package app.wooportal.server.core.messaging.notifications.definition;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.Authenticated;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class MessageDefinitionApi extends CrudApi<MessageDefinitionEntity, MessageDefinitionService> {

  public MessageDefinitionApi(
      MessageDefinitionService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getMessageDefinitions")
  @Authenticated
  public PageableList<MessageDefinitionEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getMessageDefinition")
  @Authenticated
  public Optional<MessageDefinitionEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MessageDefinitionEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveMessageDefinitions")
  @Authenticated
  public List<MessageDefinitionEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MessageDefinitionEntity> entities) {    
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveMessageDefinition")
  @Authenticated
  public MessageDefinitionEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) MessageDefinitionEntity entity) {    
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteMessageDefinitions")
  @AdminPermission
  public Boolean deleteAll(
      @GraphQLArgument(name = CrudApi.ids) List<String> ids) {    
    return super.deleteAll(ids);
  }
  
  @Override
  @GraphQLMutation(name = "deleteMessageDefinition")
  @AdminPermission
  public Boolean deleteOne(
      @GraphQLArgument(name = CrudApi.id) String id) {    
    return super.deleteOne(id);
  }

}
