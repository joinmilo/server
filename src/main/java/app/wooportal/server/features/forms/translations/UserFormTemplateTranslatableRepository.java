package app.wooportal.server.features.forms.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface UserFormTemplateTranslatableRepository
    extends TranslationRepository<UserFormTemplateTranslatableEntity> {

}
