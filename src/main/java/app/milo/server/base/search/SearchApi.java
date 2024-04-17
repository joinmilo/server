package app.milo.server.base.search;

import java.util.List;

import org.springframework.stereotype.Component;
import app.milo.server.core.base.dto.listing.FilterSortPaginate;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SearchApi {

  private final SearchService searchService;

  public SearchApi(SearchService searchService) {

    this.searchService = searchService;
  }

  @GraphQLQuery(name = "search")
  public List<SearchDto> search(FilterSortPaginate params) {
    return searchService.search(params);
  }
}
