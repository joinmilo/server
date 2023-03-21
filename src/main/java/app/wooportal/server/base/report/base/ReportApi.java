package app.wooportal.server.base.report.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ReportApi extends CrudApi<ReportEntity, ReportService> {


  public ReportApi(ReportService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getReports")
  public PageableList<ReportEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getReport")
  public Optional<ReportEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ReportEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveReports")
  @AdminPermission
  public List<ReportEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ReportEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveReport")
  public ReportEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ReportEntity entity) {
    service.preSave(entity, entity, null);
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteReports")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteReport")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
