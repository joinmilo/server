package app.wooportal.server.features.article.components.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.base.userContext.security.UserContextAuthorizationService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.captcha.CaptchaService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.article.components.media.ArticleMediaService;
import app.wooportal.server.features.article.components.publicAuthor.ArticlePublicAuthorService;

@Service
public class ArticleService extends DataService<ArticleEntity, ArticlePredicateBuilder> {

  private final CaptchaService captchaService;
  private final UserContextAuthorizationService authService;

  public ArticleService(DataRepository<ArticleEntity> repo,
      ArticlePredicateBuilder predicate,
      CaptchaService captchaService,
      ArticleMediaService articleMediaService,
      ArticlePublicAuthorService publicAuthorService,
      UserContextAuthorizationService authService) {
    super(repo, predicate);
    this.captchaService = captchaService;
    this.authService = authService;
    
    addService("publicAuthor", publicAuthorService);
    addService("uploads", articleMediaService);
  }

  @Override
  public void preCreate(ArticleEntity entity, ArticleEntity newEntity, JsonNode context) {
    var currentUser = authService.getAuthenticatedUserContext();

    if (authService.authenticatedUserHasPrivilege("articles_manage", "articles_admin", "admin")) {
      newEntity.setAuthor(currentUser.get());
      addContext("author", context);
      
      newEntity.setApproved(true);
      addContext("approved", context);
    }

  }

  public ArticleEntity saveGuestArticle(ArticleEntity entity) {
    if (isValidGuestArticle(entity)) {      
      var newEntity = new ArticleEntity();

      newEntity.setPublicAuthor(entity.getPublicAuthor());
      newEntity.setContent(entity.getContent());
      newEntity.setName(entity.getName());
      newEntity.setUploads(entity.getUploads());
      newEntity.setApproved(false);
      newEntity.setSponsored(false);
      
      persist(newEntity, newEntity, null);
    }
    return null;
  }
  
  private boolean isValidGuestArticle(ArticleEntity entity) {
    if (entity.getCaptchaToken() != null && !entity.getCaptchaToken().isEmpty()
        && entity.getPublicAuthor() != null
        && entity.getContent() != null && !entity.getContent().isBlank()
        && entity.getName() != null && !entity.getName().isBlank()) {
      captchaService.verifyToken(entity.getCaptchaToken());
      return true;
    }
    throw new BadParamsException("Invalid Guest Article", entity);
  }

  public Boolean changeApproval(String articleId) {
    var article = getById(articleId);
    
    if (article.isPresent()) {
      article.get().setApproved(!article.get().getApproved());
      repo.save(article.get());
      
      return true;
    }
    return false;
  }

  public Boolean sponsor(String articleId) {
    var article = getById(articleId);
    
    if (article.isPresent()) {
      article.get().setSponsored(true);
      repo.save(article.get());
      
      unsponsorOthers(articleId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOthers(String articleId) {
    var others = readAll(collectionQuery(predicate.withoutId(articleId)));
    if (others != null && !others.isEmpty()) {
      others.getList().stream().forEach(article -> {
        article.setSponsored(false);
        repo.save(article);
      });
    }
  }
}
