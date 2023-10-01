package app.wooportal.server.core.messaging.templates;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.Authenticated;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class MessageTemplateApi extends CrudApi<MessageTemplateEntity, MessageTemplateService> {

  public MessageTemplateApi(
      MessageTemplateService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getMessageTemplates")
  @Authenticated
  public PageableList<MessageTemplateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getMessageTemplate")
  @Authenticated
  public Optional<MessageTemplateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MessageTemplateEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveMessageTemplates")
  @Authenticated
  public List<MessageTemplateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MessageTemplateEntity> entities) {    
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveMessageTemplate")
  @Authenticated
  public MessageTemplateEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) MessageTemplateEntity entity) {    
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteMessageTemplates")
  public Boolean deleteAll(
      @GraphQLArgument(name = CrudApi.ids) List<String> ids) {    
    return super.deleteAll(ids);
  }
  
  @Override
  @GraphQLMutation(name = "deleteMessageTemplate")
  public Boolean deleteOne(
      @GraphQLArgument(name = CrudApi.id) String id) {    
    return super.deleteOne(id);
  }
}
