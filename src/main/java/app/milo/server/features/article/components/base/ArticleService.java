package app.milo.server.features.article.components.base;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.milo.server.base.userContext.security.UserContextAuthorizationService;
import app.milo.server.core.base.DataService;
import app.milo.server.core.captcha.CaptchaService;
import app.milo.server.core.config.GeneralConfiguration;
import app.milo.server.core.error.exception.BadParamsException;
import app.milo.server.core.messaging.MailService;
import app.milo.server.core.repository.DataRepository;
import app.milo.server.core.security.components.user.UserService;
import app.milo.server.features.article.components.media.ArticleMediaService;
import app.milo.server.features.article.components.publicAuthor.ArticlePublicAuthorService;

@Service
public class ArticleService extends DataService<ArticleEntity, ArticlePredicateBuilder> {

  private final UserContextAuthorizationService authService;
  private final CaptchaService captchaService;
  private final GeneralConfiguration config;
  private final MailService mailService;
  private final UserService userService;

  public ArticleService(DataRepository<ArticleEntity> repo,
      ArticlePredicateBuilder predicate,
      ArticleMediaService articleMediaService,
      UserContextAuthorizationService authService,
      CaptchaService captchaService,
      GeneralConfiguration config,
      ArticlePublicAuthorService publicAuthorService,
      MailService mailService,
      UserService userService) {
    super(repo, predicate);
    
    this.authService = authService;
    this.captchaService = captchaService;
    this.config = config;
    this.mailService = mailService;
    this.userService = userService;
    
    addService("publicAuthor", publicAuthorService);
    addService("uploads", articleMediaService);
  }

  @Override
  public void preCreate(ArticleEntity entity, ArticleEntity newEntity, JsonNode context) {
    newEntity.setSponsored(false);
    addContext("sponsored", context);
    
    newEntity.setApproved(false);
    addContext("approved", context);

    var currentUser = authService.getAuthenticatedUserContext();
    
    if (authService.authenticatedUserHasPrivilege("articles_manage", "articles_admin") && currentUser.isPresent()) {
      newEntity.setApproved(true);
      addContext("approved", context);
      newEntity.setAuthor(currentUser.get());
      addContext("author", context);
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
      
      this.userService.getUsersWithPrivileges("articles_admin", "admin").stream().forEach(user -> {
        try {
          mailService.sendEmail("Neuer Gastartikel", "newGuestArticle.ftl",
              Map.of(
                  "userName" , user.getFirstName() != null ? " " + user.getFirstName() : "",
                  "portalName", config.getPortalName(),
                  "name", entity.getName(),
                  "link", createApproveGuestArticleLink()),
              user.getEmail());
        } catch (Throwable e) {
          e.printStackTrace();
        }
      });
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
      
      try {
        System.out.println(article.get().getPublicAuthor().getEmail());
        mailService.sendEmail("Artikel freigegeben", "approvedArticle.ftl", Map.of(
                  "author", article.get().getPublicAuthor().getName(),
                  "title", article.get().getName(),
                  "link", createArtikelDetailsLink(article.get().getSlug()),
                  "portalName", config.getPortalName()),
            article.get().getPublicAuthor().getEmail());
      } catch (Throwable e) {
        e.printStackTrace();
      } ;

      return true;
    }
    return false;
  }

  public Boolean sponsor(String articleId) {
    var article = getById(articleId);

    if (article.isPresent()) {
      article.get().setSponsored(true);
      repo.save(article.get());

      unsponsorOther(articleId);

      return true;
    }
    return false;
  }

  private void unsponsorOther(String articleId) {
    var other = readAll(collectionQuery(
        predicate.withSponsoredTrue()
          .and(predicate.withoutId(articleId))));
    if (other != null && !other.isEmpty()) {
      other.getList().stream().forEach(article -> {
        article.setSponsored(false);
        repo.save(article);
      });
    }
  }
  
  private String createArtikelDetailsLink(String slug) {
    return config.getHost() + "/portal/articles/" + slug;
  }
  
  private String createApproveGuestArticleLink() {
    return config.getHost() + "/admin/guestarticle";
  }  
}
