package app.wooportal.server.core.messaging.template;

import java.util.Map;

import app.wooportal.server.core.i18n.components.language.LanguageEntity;

public interface TemplateService {
  
  public String createMessage(String templateName, Map<String, String> model, LanguageEntity language) throws Exception, Throwable;

}
