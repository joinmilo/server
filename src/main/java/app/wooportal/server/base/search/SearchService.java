package app.wooportal.server.base.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.cms.feature.FeatureEntity;
import app.wooportal.server.base.cms.feature.FeatureService;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.base.userContext.base.UserContextService;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.features.article.base.ArticleEntity;
import app.wooportal.server.features.article.base.ArticleService;
import app.wooportal.server.features.contest.base.ContestEntity;
import app.wooportal.server.features.contest.base.ContestService;
import app.wooportal.server.features.deal.base.DealEntity;
import app.wooportal.server.features.deal.base.DealService;
import app.wooportal.server.features.event.base.EventEntity;
import app.wooportal.server.features.event.base.EventService;
import app.wooportal.server.features.organisation.base.OrganisationEntity;
import app.wooportal.server.features.organisation.base.OrganisationService;
import app.wooportal.server.features.survey.base.SurveyEntity;
import app.wooportal.server.features.survey.base.SurveyService;

@Service
public class SearchService {

  private final EventService eventService;
  private final OrganisationService organisationService;
  private final ArticleService articleService;
  private final DealService dealService;
  private final ContestService contestService;
  private final SurveyService surveyService;
  private final UserContextService userContextService;
  private final FeatureService featureService;

  public SearchService(EventService eventService, OrganisationService organisationService,
      ArticleService articleService, DealService dealService, ContestService contestService,
      SurveyService surveyService, UserContextService userContextService,
      FeatureService featureService) throws IOException {

    this.eventService = eventService;
    this.organisationService = organisationService;
    this.articleService = articleService;
    this.contestService = contestService;
    this.dealService = dealService;
    this.surveyService = surveyService;
    this.userContextService = userContextService;
    this.featureService = featureService;
    
  }

  public List<SearchDto> search(FilterSortPaginate params) {

    var list = new ArrayList<SearchDto>();

    var entities = new ArrayList<BaseEntity>();
    entities.addAll(eventService.readAll(params).getList());
    entities.addAll(organisationService.readAll(params).getList());
    entities.addAll(articleService.readAll(params).getList());
    entities.addAll(contestService.readAll(params).getList());
    entities.addAll(dealService.readAll(params).getList());
    entities.addAll(surveyService.readAll(params).getList());
    entities.addAll(userContextService.readAll(params).getList());
    
    var features = featureService.readAll().getList();

    // TODO Translatable names etc

    for (var entity : entities) {
      if (entity instanceof EventEntity) {
        var event = (EventEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(event.getId());
        searchResult.setFeature(getFeature("event", features));
        list.add(searchResult);

      } else if (entity instanceof OrganisationEntity) {
        var organisation = (OrganisationEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(organisation.getId());
        searchResult.setFeature(getFeature("organisation", features));
        list.add(searchResult);

      } else if (entity instanceof ArticleEntity) {
        var article = (ArticleEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(article.getId());
        searchResult.setFeature(getFeature("article", features));
        list.add(searchResult);

      } else if (entity instanceof ContestEntity) {
        var contest = (ContestEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(contest.getId());
        searchResult.setFeature(getFeature("contest", features));
        list.add(searchResult);

      } else if (entity instanceof DealEntity) {
        var deal = (DealEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(deal.getId());
        searchResult.setFeature(getFeature("deal", features));
        list.add(searchResult);

      } else if (entity instanceof SurveyEntity) {
        var survey = (SurveyEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(survey.getId());
        searchResult.setFeature(getFeature("survey", features));
        list.add(searchResult);

      } else if (entity instanceof UserContextEntity
          && ((UserContextEntity) entity).getArticles() != null) {
        var author = (UserContextEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(author.getId());
        searchResult.setFeature(getFeature("author", features));
        list.add(searchResult);
      }
    }
    return params.getSize() != null && list.size() > params.getSize()
        ? list.subList(0, params.getSize())
        : list;
  }
  private FeatureEntity getFeature(String key, List<FeatureEntity> features) {
    for(var feature : features) {
      if (feature.getKey() == key) {
        return feature;
      }
    }
    return null;
  }
}

