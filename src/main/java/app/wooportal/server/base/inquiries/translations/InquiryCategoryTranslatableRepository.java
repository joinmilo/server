package app.wooportal.server.base.inquiry.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;
import app.wooportal.server.features.articles.articleCategories.translations.ArticleCategoryTranslatableEntity;

@Repository
public interface InquiryCategoryTranslatableRepository
    extends TranslationRepository<ArticleCategoryTranslatableEntity> {
}
          