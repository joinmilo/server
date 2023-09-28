package app.wooportal.server.features.article.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.captcha.CaptchaService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.services.AuthenticationService;
import app.wooportal.server.features.article.media.ArticleMediaService;
import app.wooportal.server.features.article.publicAuthor.ArticlePublicAuthorService;

@Service
public class ArticleService extends DataService<ArticleEntity, ArticlePredicateBuilder> {

  private final CaptchaService captchaService;
  private final AuthenticationService authService;

  public ArticleService(DataRepository<ArticleEntity> repo,
      ArticlePredicateBuilder predicate,
      CaptchaService captchaService,
      ArticleMediaService articleMediaService,
      ArticlePublicAuthorService publicAuthorService,
      AuthenticationService authService) {
    super(repo, predicate);
    this.captchaService = captchaService;
    this.authService = authService;
    
    addService("publicAuthor", publicAuthorService);
    addService("uploads", articleMediaService);
  }

  @Override
  public void preCreate(ArticleEntity entity, ArticleEntity newEntity, JsonNode context) {

    //TODO privilege 
    if (newEntity.getSponsored() == null) {
      newEntity.setSponsored(false);
      addContext("sponsored", context);
    }

    var currentUser = authService.getAuthenticatedUser();

    if (currentUser != null && !currentUser.isEmpty()) {
      newEntity.setAuthor(currentUser.get().getUserContext());
      addContext("author", context);

      if (this.validatePrivilege(currentUser.get())) {
        newEntity.setApproved(true);
        addContext("approved", context);
      }

      else {

        newEntity.setApproved(false);
        addContext("approved", context);

        if (newEntity.getCaptchaToken() != null && !newEntity.getCaptchaToken().isEmpty()) {
          captchaService.verifyToken(newEntity.getCaptchaToken());
        } else {
          throw new BadParamsException("Captcha token empty or null", null);
        }
      }
    }
  }
  
  private boolean validatePrivilege(UserEntity currentUser) {
    return currentUser.getRoles().stream()
            .flatMap(role -> role.getPrivileges().stream())
            .anyMatch(privilege -> "articles-manage".equals(privilege.getCode()) 
                || "admin".equals(privilege.getCode()) 
                || "manage".equals(privilege.getCode()) 
                || "articles-admin".equals(privilege.getCode()));
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
