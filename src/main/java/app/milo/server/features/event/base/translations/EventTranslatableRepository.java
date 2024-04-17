package app.milo.server.features.event.base.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface EventTranslatableRepository
    extends TranslationRepository<EventTranslatableEntity> {
  
}
