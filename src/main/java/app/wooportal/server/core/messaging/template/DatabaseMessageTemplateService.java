package app.wooportal.server.core.messaging.template;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.messaging.notifications.definition.MessageDefinitionEntity;
import app.wooportal.server.core.messaging.notifications.template.MessageTemplateService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class DatabaseMessageTemplateService implements TemplateService {

  private final MessageTemplateService service;

  public DatabaseMessageTemplateService(MessageTemplateService service) {
    this.service = service;
  }

  @Override
  public String createMessage(String templateName, Map<String, String> model) throws Exception {
    var messageTemplate =
        service.readOne(service.singleQuery(service.getPredicate().withName(templateName)));
    if (messageTemplate.isEmpty()) {
      throw new IllegalArgumentException("Template with " + templateName + " does not exist");
    }

    var message = messageTemplate.get().getContent();
    if (message != null && !message.isBlank()) {

      for (var entry : model.entrySet()) {
        message =
            message.replaceFirst(String.format("\\$\\{%s\\}", entry.getKey()), entry.getValue());
      }
    }
    return message;
  }

  public Map<String, String> createMessageModel(MessageDefinitionEntity notificationDef,
      Object entity) {
    var result = new HashMap<String, String>();

    if (entity != null && notificationDef != null && notificationDef.getTemplate() != null
        && notificationDef.getTemplate().getContent() != null) {
      var matcher =
          Pattern.compile("\\$\\{(.*?)\\}").matcher(notificationDef.getTemplate().getContent());

      while (matcher.find()) {
        var fields = Arrays.asList(matcher.group(1).split("\\."));
        var current = entity;
        for (var fieldName : fields) {
          current = Hibernate.unproxy(ReflectionUtils.get(fieldName, current).get());
        }
        result.put(matcher.group(1), format(current));
      }
    }
    return result;
  }

  public String format(Object current) {

    if (TemporalAccessor.class.isAssignableFrom(current.getClass())) {
      var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
      return formatter.format((TemporalAccessor) current);
    }

    return current.toString();
  }

}
