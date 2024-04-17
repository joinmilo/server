package app.milo.server.base.userContext.favorite;

import java.util.Optional;

import org.springframework.stereotype.Component;
import app.milo.server.base.userContext.base.UserContextEntity;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class FavoriteApi {
  
  private final FavoriteService service;

  public FavoriteApi(FavoriteService service) {
    this.service = service;
  }
  
  @GraphQLMutation(name = "addFavoriteArticle")
  public Optional<UserContextEntity> addFavoriteArticle(String articleId) {
    return service.addFavoriteArticle(articleId);
  }

  @GraphQLMutation(name = "removeFavoriteArticle")
  public Optional<UserContextEntity> removeFavoriteArticle(String articleId) {
    return service.removeFavoriteArticle(articleId);
  }
  
  @GraphQLMutation(name = "addFavoriteAuthor")
  public Optional<UserContextEntity> addFavoriteAuthor(String userContextId) {
    return service.addFavoriteAuthor(userContextId);
  }

  @GraphQLMutation(name = "removeFavoriteAuthor")
  public Optional<UserContextEntity> removeFavoriteAuthor(String userContextId) {
    return service.removeFavoriteAuthor(userContextId);
  }
  
  @GraphQLMutation(name = "addFavoriteDeal")
  public Optional<UserContextEntity> addFavoriteDeal(String dealId) {
    return service.addFavoriteDeal(dealId);
  }

  @GraphQLMutation(name = "removeFavoriteDeal")
  public Optional<UserContextEntity> removeFavoriteDeal(String dealId) {
    return service.removeFavoriteDeal(dealId);
  }
  
  @GraphQLMutation(name = "addFavoriteEvent")
  public Optional<UserContextEntity> addFavoriteEvent(String eventId) {
    return service.addFavoriteEvent(eventId);
  }

  @GraphQLMutation(name = "removeFavoriteEvent")
  public Optional<UserContextEntity> removeFavoriteEvent(String eventId) {
    return service.removeFavoriteEvent(eventId);
  }
  
  @GraphQLMutation(name = "addFavoriteOrganisation")
  public Optional<UserContextEntity> addFavoriteOrganisation(String organisationId) {
    return service.addFavoriteOrganisation(organisationId);
  }

  @GraphQLMutation(name = "removeFavoriteOrganisation")
  public Optional<UserContextEntity> removeFavoriteOrganisation(String organisationId) {
    return service.removeFavoriteOrganisation(organisationId);
  }
}
