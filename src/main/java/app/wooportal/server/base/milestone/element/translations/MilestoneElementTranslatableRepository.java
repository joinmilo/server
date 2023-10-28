package app.wooportal.server.base.milestone.element.translations;


import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface MilestoneElementTranslatableRepository
    extends TranslationRepository<MilestoneElementTranslatableEntity> {
}
