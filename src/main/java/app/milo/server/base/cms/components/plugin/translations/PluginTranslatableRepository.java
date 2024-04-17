package app.milo.server.base.cms.components.plugin.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface PluginTranslatableRepository
    extends TranslationRepository<PluginTranslatableEntity> {

}
