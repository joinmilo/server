package app.milo.server.core.messaging.template;

import java.util.Map;
import app.milo.server.core.i18n.components.language.LanguageEntity;

public interface TemplateService {
  
  public String createMessage(String templateName, Map<String, String> model, LanguageEntity language) throws Exception, Throwable;

}
