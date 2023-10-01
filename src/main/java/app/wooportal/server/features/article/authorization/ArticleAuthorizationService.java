package app.wooportal.server.features.article.authorization;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import app.wooportal.server.base.userContext.security.UserContextAuthorizationService;
import app.wooportal.server.features.article.components.base.ArticleEntity;
import app.wooportal.server.features.article.components.base.ArticleService;

@Service
public class ArticleAuthorizationService {
  
  private final UserContextAuthorizationService authorizationService;
  private final ArticleService articleService;
  
  public ArticleAuthorizationService(
      UserContextAuthorizationService authorizationService,
      ArticleService articleService) {
    this.articleService = articleService;
    this.authorizationService = authorizationService;
  }
  
  public boolean isOwn(Authentication authentication, ArticleEntity entity) {
    if (entity.getId() != null && !entity.getId().isBlank()) {
      var userContext = authorizationService.getUserContext(authentication);
      if (!userContext.isEmpty()) {
        return articleService.exists(
            articleService.getPredicate().withId(entity.getId())
              .and(articleService.getPredicate().withAuthor(userContext.get().getId()))
        );
      }
    }
    return true;
  }

}
