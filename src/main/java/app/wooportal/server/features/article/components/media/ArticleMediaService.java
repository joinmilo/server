package app.wooportal.server.features.article.components.media;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ArticleMediaService
    extends DataService<ArticleMediaEntity, ArticleMediaPredicateBuilder> {

  public ArticleMediaService(
      DataRepository<ArticleMediaEntity> repo,
      ArticleMediaPredicateBuilder predicate,
      MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
  
  @Override
  public Optional<ArticleMediaEntity> getExisting(ArticleMediaEntity entity) {
    return entity != null
        && entity.getMedia() != null
        && entity.getMedia().getId() != null
        && entity.getArticle() != null
        && entity.getArticle().getId() != null
       ? repo.findOne(singleQuery(predicate.withMedia(entity.getMedia().getId()))
           .and(predicate.withArticle(entity.getArticle().getId()))
         )
       : Optional.empty();
  }
}
