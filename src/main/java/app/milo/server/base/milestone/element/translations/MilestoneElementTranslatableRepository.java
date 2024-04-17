package app.milo.server.base.milestone.element.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface MilestoneElementTranslatableRepository
    extends TranslationRepository<MilestoneElementTranslatableEntity> {
}
