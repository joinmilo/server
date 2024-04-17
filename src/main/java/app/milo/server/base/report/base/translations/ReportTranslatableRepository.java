package app.milo.server.base.report.base.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ReportTranslatableRepository
    extends TranslationRepository<ReportTranslatableEntity> {
}
