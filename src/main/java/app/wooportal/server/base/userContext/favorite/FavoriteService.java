package app.wooportal.server.base.userContext.favorite;

import java.util.Optional;

import org.springframework.stereotype.Service;

import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.base.userContext.base.UserContextService;
import app.wooportal.server.features.article.components.base.ArticleService;
import app.wooportal.server.features.deal.components.base.DealService;
import app.wooportal.server.features.event.base.EventService;
import app.wooportal.server.features.organisation.base.OrganisationService;

@Service
public class FavoriteService {

  private final ArticleService articleService;
  
  private final DealService dealService;

  private final EventService eventService;
  
  private final OrganisationService organisationService;

  private final UserContextService userContextService;

  public FavoriteService(
      ArticleService articleService,
      DealService dealService,
      EventService eventService,
      OrganisationService organisationService,
      UserContextService userContextService) {

    this.articleService = articleService;
    this.dealService = dealService;
    this.eventService = eventService;
    this.organisationService = organisationService;
    this.userContextService = userContextService;
  }

  public Optional<UserContextEntity> addFavoriteArticle(String articleId) {
    var currentUser = userContextService.me();
    var event = articleService.getById(articleId);
    if (currentUser.isPresent() && event.isPresent()
        && !currentUser.get().getFavoriteArticles().contains(event.get())) {

      currentUser.get().getFavoriteArticles().add(event.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }

  public Optional<UserContextEntity> removeFavoriteArticle(String articleId) {
    var currentUser = userContextService.me();
    var article = articleService.getById(articleId);
    if (currentUser.isPresent() && article.isPresent()) {
      currentUser.get().getFavoriteArticles().remove(article.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserContextEntity> addFavoriteAuthor(String userContextId) {
    var currentUser = userContextService.me();
    var author = userContextService.getById(userContextId);
    if (currentUser.isPresent() && author.isPresent()
        && !currentUser.get().getFavoriteAuthors().contains(author.get())) {

      currentUser.get().getFavoriteAuthors().add(author.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }

  public Optional<UserContextEntity> removeFavoriteAuthor(String userContextId) {
    var currentUser = userContextService.me();
    var author = userContextService.getById(userContextId);
    if (currentUser.isPresent() && author.isPresent()) {
      currentUser.get().getFavoriteAuthors().remove(author.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserContextEntity> addFavoriteDeal(String dealId) {
    var currentUser = userContextService.me();
    var deal = dealService.getById(dealId);
    if (currentUser.isPresent() && deal.isPresent()
        && !currentUser.get().getFavoriteDeals().contains(deal.get())) {

      currentUser.get().getFavoriteDeals().add(deal.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }

  public Optional<UserContextEntity> removeFavoriteDeal(String dealId) {
    var currentUser = userContextService.me();
    var deal = dealService.getById(dealId);
    if (currentUser.isPresent() && deal.isPresent()) {
      currentUser.get().getFavoriteDeals().remove(deal.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }

  public Optional<UserContextEntity> addFavoriteEvent(String eventId) {
    var currentUser = userContextService.me();
    var event = eventService.getById(eventId);
    if (currentUser.isPresent() && event.isPresent()
        && !currentUser.get().getFavoriteEvents().contains(event.get())) {

      currentUser.get().getFavoriteEvents().add(event.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }

  public Optional<UserContextEntity> removeFavoriteEvent(String eventId) {
    var currentUser = userContextService.me();
    var event = eventService.getById(eventId);
    if (currentUser.isPresent() && event.isPresent()) {
      currentUser.get().getFavoriteEvents().remove(event.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserContextEntity> addFavoriteOrganisation(String organisationId) {
    var currentUser = userContextService.me();
    var organisation = organisationService.getById(organisationId);
    if (currentUser.isPresent() && organisation.isPresent()
        && !currentUser.get().getFavoriteOrganisations().contains(organisation.get())) {

      currentUser.get().getFavoriteOrganisations().add(organisation.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }

  public Optional<UserContextEntity> removeFavoriteOrganisation(String organisationId) {
    var currentUser = userContextService.me();
    var organisation = organisationService.getById(organisationId);
    if (currentUser.isPresent() && organisation.isPresent()) {
      currentUser.get().getFavoriteOrganisations().remove(organisation.get());
      return Optional.of(userContextService.save(currentUser.get()));
    }
    return currentUser;
  }

}
