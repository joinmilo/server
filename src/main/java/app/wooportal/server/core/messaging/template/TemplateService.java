package app.wooportal.server.core.messaging.template;

import java.util.Map;

public interface TemplateService {
  
  public String createMessage(String templateName, Map<String, String> model) throws Exception;

}
