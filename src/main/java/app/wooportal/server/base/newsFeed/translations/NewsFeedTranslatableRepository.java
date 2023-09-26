package app.wooportal.server.base.newsFeed.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface NewsFeedTranslatableRepository
    extends TranslationRepository<NewsFeedTranslatableEntity> {
}
