package app.wooportal.server.core.information;

import org.springframework.stereotype.Component;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class InformationApi {

  private final InformationService infoService;

  public InformationApi(InformationService infoService) {
    this.infoService = infoService;
  }

  @GraphQLQuery(name = "getInformation")
  public InformationDto info() {
    return infoService.getInfo();
  }
}
