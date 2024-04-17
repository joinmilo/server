package app.milo.server.base.milestone.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface MilestoneTranslatableRepository
    extends TranslationRepository<MilestoneTranslatableEntity> {
}
