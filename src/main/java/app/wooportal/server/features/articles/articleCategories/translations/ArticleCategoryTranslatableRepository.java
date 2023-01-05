package app.wooportal.server.features.articles.articleCategories.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ArticleCategoryTranslatableRepository
    extends TranslationRepository<ArticleCategoryTranslatableEntity> {
}
          