package app.wooportal.server.base.analytics.search;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.searchconsole.v1.SearchConsole;
import com.google.api.services.searchconsole.v1.model.ApiDataRow;
import app.wooportal.server.base.analytics.search.dto.SearchConsoleQuery;

@Service
public class SearchConsoleApiService {
  
  private final SearchConsoleConfig config;
  
  public SearchConsoleApiService(
      SearchConsoleConfig searchConfig) {
    this.config = searchConfig;
  }

  public List<ApiDataRow> execute(SearchConsoleQuery query) throws IOException {
    return createSearchService()
      .searchanalytics()
      .query(config.getHost(), query.getQuery())
      .execute()
      .getRows();
  }
  
  public SearchConsole createSearchService() throws IOException {
//  // As of now, this cannot be passed to SearchConsole.Builder
//  var credentials = GoogleCredentials
//    .fromStream(new ClassPathResource(searchConfig.getCredentials())
//        .getInputStream())
//    .createScoped(Collections.singletonList(searchConfig.getScope()));
  
  // Only the deprecated method works
  @SuppressWarnings("deprecation")
  var credentials = GoogleCredential
    .fromStream(new ClassPathResource(config.getCredentials())
        .getInputStream())
    .createScoped(Collections.singletonList(config.getScope()));

  return new SearchConsole.Builder(
      new NetHttpTransport(), 
      new GsonFactory(), 
      credentials).build();
  }

}
