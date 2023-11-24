package app.wooportal.server.base.cms.plugin.translations;


import org.springframework.stereotype.Repository;

import app.wooportal.server.core.i18n.translation.TranslationRepository;

@Repository
public interface PluginTranslatableRepository
    extends TranslationRepository<PluginTranslatableEntity> {

}
