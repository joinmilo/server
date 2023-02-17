package app.wooportal.server.features.articles.comments.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ArticleCommentTranslatableRepository
    extends TranslationRepository<ArticleCommentTranslatableEntity> {

}
