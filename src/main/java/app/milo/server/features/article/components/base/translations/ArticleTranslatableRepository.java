package app.milo.server.features.article.components.base.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ArticleTranslatableRepository
    extends TranslationRepository<ArticleTranslatableEntity> {
}
