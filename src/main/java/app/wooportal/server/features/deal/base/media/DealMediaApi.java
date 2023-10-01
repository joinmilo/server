package app.wooportal.server.features.deal.base.media;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class DealMediaApi extends CrudApi<DealMediaEntity, DealMediaService> {

  public DealMediaApi(DealMediaService service) {
    super(service);
  }
  
  @Override
  @GraphQLQuery(name = "getDealMedia")
  public PageableList<DealMediaEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getDealMedium")
  public Optional<DealMediaEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) DealMediaEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveDealMedia")
  public List<DealMediaEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<DealMediaEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveDealMedium")
  public DealMediaEntity saveOne(@GraphQLArgument(name = CrudApi.entity) DealMediaEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteDealMedia")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteDealMedium")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
