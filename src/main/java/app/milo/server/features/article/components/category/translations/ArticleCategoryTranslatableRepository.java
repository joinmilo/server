package app.milo.server.features.article.components.category.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ArticleCategoryTranslatableRepository
    extends TranslationRepository<ArticleCategoryTranslatableEntity> {
}
          