package app.wooportal.server.features.article.category.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ArticleCategoryTranslatableRepository
    extends TranslationRepository<ArticleCategoryTranslatableEntity> {
}
          