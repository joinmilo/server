package app.wooportal.server.features.article.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.captcha.CaptchaService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.article.publicAuthor.PublicAuthorService;

@Service
public class ArticleService extends DataService<ArticleEntity, ArticlePredicateBuilder> {

  private final CaptchaService captchaService;

  public ArticleService(DataRepository<ArticleEntity> repo,
      ArticlePredicateBuilder predicate,
      CaptchaService captchaService,
      PublicAuthorService publicAuthorService) {
    super(repo, predicate);
    this.captchaService = captchaService;
    
    addService("publicAuthor", publicAuthorService);
  }

  @Override
  public void preSave(ArticleEntity entity, ArticleEntity newEntity, JsonNode context) {

    // TODO: Make an hook for specific validations
    if (newEntity.getCaptchaToken() != null && !newEntity.getCaptchaToken().isEmpty()) {
      captchaService.verifyToken(newEntity.getCaptchaToken());
    } else {
      throw new BadParamsException("Captcha token empty or null", null);
    }
  }
}
