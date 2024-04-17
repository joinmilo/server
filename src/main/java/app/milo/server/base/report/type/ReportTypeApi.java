package app.milo.server.base.report.type;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.milo.server.core.base.CrudApi;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import app.milo.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ReportTypeApi extends CrudApi<ReportTypeEntity, ReportTypeService> {


  public ReportTypeApi(ReportTypeService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getReportTypes")
  public PageableList<ReportTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getReportType")
  public Optional<ReportTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ReportTypeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveReportTypes")
  public List<ReportTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ReportTypeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveReportType")
  public ReportTypeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ReportTypeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteReportTypes")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteReportType")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
