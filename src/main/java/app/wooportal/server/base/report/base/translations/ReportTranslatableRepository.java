package app.wooportal.server.base.report.base.translations;


import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ReportTranslatableRepository
    extends TranslationRepository<ReportTranslatableEntity> {
}
