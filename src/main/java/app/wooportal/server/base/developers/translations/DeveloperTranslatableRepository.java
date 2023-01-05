package app.wooportal.server.base.developers.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;
import app.wooportal.server.features.articles.articleCategories.translations.ArticleCategoryTranslatableEntity;

@Repository
public interface DeveloperTranslatableRepository
    extends TranslationRepository<ArticleCategoryTranslatableEntity> {
}
          