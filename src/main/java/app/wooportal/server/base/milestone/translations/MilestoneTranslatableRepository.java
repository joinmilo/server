package app.wooportal.server.base.milestone.translations;


import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface MilestoneTranslatableRepository
    extends TranslationRepository<MilestoneTranslatableEntity> {
}
