package app.wooportal.server.base.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import app.wooportal.server.base.cms.plugin.PluginEntity;
import app.wooportal.server.base.cms.plugin.PluginService;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.base.userContext.base.UserContextService;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.features.article.components.base.ArticleEntity;
import app.wooportal.server.features.article.components.base.ArticleService;
import app.wooportal.server.features.contest.base.ContestEntity;
import app.wooportal.server.features.contest.base.ContestService;
import app.wooportal.server.features.deal.components.base.DealEntity;
import app.wooportal.server.features.deal.components.base.DealService;
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
  private final PluginService pluginService;

  public SearchService(EventService eventService, OrganisationService organisationService,
      ArticleService articleService, DealService dealService, ContestService contestService,
      SurveyService surveyService, UserContextService userContextService,
      PluginService pluginService) throws IOException {

    this.eventService = eventService;
    this.organisationService = organisationService;
    this.articleService = articleService;
    this.contestService = contestService;
    this.dealService = dealService;
    this.surveyService = surveyService;
    this.userContextService = userContextService;
    this.pluginService = pluginService;
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

    var plugins = pluginService.readAll().getList();
    // TODO Translatable names etc
    
    for (var entity : entities) {
      if (entity instanceof EventEntity) {
        var event = (EventEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setSlug(event.getSlug());
        searchResult.setPlugin(getPlugin("events", plugins));
        list.add(searchResult);

      } else if (entity instanceof OrganisationEntity) {
        var organisation = (OrganisationEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setSlug(organisation.getSlug());
        searchResult.setPlugin(getPlugin("organisations", plugins));
        list.add(searchResult);

      } else if (entity instanceof ArticleEntity) {
        var article = (ArticleEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setSlug(article.getSlug());
        searchResult.setPlugin(getPlugin("articles", plugins));
        list.add(searchResult);

      } else if (entity instanceof ContestEntity) {
        var contest = (ContestEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setSlug(contest.getSlug());
        searchResult.setPlugin(getPlugin("contests", plugins));
        list.add(searchResult);

      } else if (entity instanceof DealEntity) {
        var deal = (DealEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setSlug(deal.getSlug());
        searchResult.setPlugin(getPlugin("deals", plugins));
        list.add(searchResult);

      } else if (entity instanceof SurveyEntity) {
        var survey = (SurveyEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setSlug(survey.getSlug());
        searchResult.setPlugin(getPlugin("surveys", plugins));
        list.add(searchResult);

      } else if (entity instanceof UserContextEntity
          && ((UserContextEntity) entity).getArticles() != null) {
        var author = (UserContextEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setSlug(author.getSlug());
        searchResult.setPlugin(getPlugin("authors", plugins));
        list.add(searchResult);
      }
    }
    return params.getSize() != null && list.size() > params.getSize()
        ? list.subList(0, params.getSize())
        : list;
  }

  private PluginEntity getPlugin(String code, List<PluginEntity> plugins) {
    for (var plugin : plugins) {
      if (plugin.getCode().equals(code)) {
        return plugin;
      }
    }
    return null;
  }
}

