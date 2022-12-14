package app.wooportal.server.features.articles.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface ArticleTranslatableRepository
    extends TranslationRepository<ArticleCategoryTranslatableEntity> {
}
          