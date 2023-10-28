package app.wooportal.server.core.messaging.template;

import java.io.IOException;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import app.wooportal.server.core.i18n.components.language.LanguageEntity;
import app.wooportal.server.core.messaging.MailConfiguration;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
@Primary
public class FreemarkerTemplateService implements TemplateService {

  private final Configuration freemarkerConfig;

  private final MailConfiguration mailConfig;

  public FreemarkerTemplateService(
      Configuration freemarkerConfig,
      MailConfiguration mailConfig) {
    this.freemarkerConfig = freemarkerConfig;
    this.mailConfig = mailConfig;

    this.freemarkerConfig.setClassForTemplateLoading(this.getClass(),
        this.mailConfig.getTemplateLocation());
  }

  public String createMessage(String templateName, Map<String, String> model, LanguageEntity language)
      throws TemplateException, IOException {
    return FreeMarkerTemplateUtils.processTemplateIntoString(
        freemarkerConfig.getTemplate(templateName),
        model);
  }

}
