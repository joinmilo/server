package app.wooportal.server.base.analytics.googleSearch.query;

public enum SearchConsoleDataState {
  
  /**
   * data will include fresh data
   * @see https://developers.google.com/search/blog/2019/09/search-performance-fresh-data
   */
  ALL,
  
  /**
   * data will include only finalized data
   */
  FINAL;

}
