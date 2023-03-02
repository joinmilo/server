package app.wooportal.server.features.article.base.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;
import app.wooportal.server.features.article.category.translations.ArticleCategoryTranslatableEntity;

@Repository
public interface ArticleTranslatableRepository
    extends TranslationRepository<ArticleCategoryTranslatableEntity> {
}
          