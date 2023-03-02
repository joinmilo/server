package app.wooportal.server.core.i18n.components.label.translations;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface LabelTranslatableRepository
    extends TranslationRepository<LabelTranslatablesEntity> {
}
