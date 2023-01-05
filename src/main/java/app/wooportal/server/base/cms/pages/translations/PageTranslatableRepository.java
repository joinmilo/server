package app.wooportal.server.base.cms.pages.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface PageTranslatableRepository extends TranslationRepository<PageTranslatableEntity> {

}
