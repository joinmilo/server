package app.milo.server.features.article.components.comment.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ArticleCommentTranslatableRepository
    extends TranslationRepository<ArticleCommentTranslatableEntity> {

}
