package app.wooportal.server.base.report.type.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ReportTypeTranslatableRepository
    extends TranslationRepository<ReportTypeTranslatableEntity> {
}
