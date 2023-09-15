package app.wooportal.server.features.article.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.captcha.CaptchaService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.article.media.ArticleMediaService;
import app.wooportal.server.features.article.publicAuthor.ArticlePublicAuthorService;

@Service
public class ArticleService extends DataService<ArticleEntity, ArticlePredicateBuilder> {

  private final CaptchaService captchaService;

  public ArticleService(DataRepository<ArticleEntity> repo,
      ArticlePredicateBuilder predicate,
      CaptchaService captchaService,
      ArticleMediaService articleMediaService,
      ArticlePublicAuthorService publicAuthorService) {
    super(repo, predicate);
    this.captchaService = captchaService;
    
    addService("publicAuthor", publicAuthorService);
    addService("uploads", articleMediaService);
  }

  @Override
  public void preCreate(ArticleEntity entity, ArticleEntity newEntity, JsonNode context) {

    // TODO: Make an hook for specific validations
    if (newEntity.getCaptchaToken() != null && !newEntity.getCaptchaToken().isEmpty()) {
      captchaService.verifyToken(newEntity.getCaptchaToken());
    } else {
      throw new BadParamsException("Captcha token empty or null", null);
    }
    
    if (newEntity.getSponsored() == null) {
      newEntity.setSponsored(false);
      addContext("sponsored", context);
    }
    
    //TODO: This is easy to break
    if (newEntity.getAuthor() == null) {      
      newEntity.setApproved(false);
      addContext("approved", context);
    }
  }

  public Boolean sponsorArticle(String articleId) {
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
