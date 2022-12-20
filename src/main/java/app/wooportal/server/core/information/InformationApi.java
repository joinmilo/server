package app.wooportal.server.core.information;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.stereotype.Component;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class InformationApi {

  private final InformationService versionService;

  public InformationApi(InformationService versionService) {

    this.versionService = versionService;
  }

  @GraphQLQuery(name = "getVersion")
  public InformationDto version()
      throws FileNotFoundException, IOException, XmlPullParserException {
    return versionService.getVersion();
  }
}
