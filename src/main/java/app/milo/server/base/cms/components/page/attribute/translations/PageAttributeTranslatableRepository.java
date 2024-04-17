package app.milo.server.base.cms.components.page.attribute.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface PageAttributeTranslatableRepository
    extends TranslationRepository<PageAttributeTranslatableEntity> {

}
