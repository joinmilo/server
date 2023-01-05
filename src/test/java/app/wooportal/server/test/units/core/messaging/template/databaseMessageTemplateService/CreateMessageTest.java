package app.wooportal.server.test.units.core.messaging.template.databaseMessageTemplateService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.core.messaging.services.DatabaseMessageTemplateService;
import app.wooportal.server.core.messaging.templates.MessageTemplateEntity;
import app.wooportal.server.core.messaging.templates.MessageTemplatePredicateBuilder;
import app.wooportal.server.core.messaging.templates.MessageTemplateRepository;
import app.wooportal.server.core.messaging.templates.MessageTemplateService;

public class CreateMessageTest {

  private DatabaseMessageTemplateService databaseMessageTemplateService =
      new DatabaseMessageTemplateService(service, null);

  private static MessageTemplateService service;

  @BeforeAll
  public static void init() {
    var messageTemplateRepository = mock(MessageTemplateRepository.class);
    service = new MessageTemplateService(messageTemplateRepository,
        new MessageTemplatePredicateBuilder());
  }

  @Test
  public void checkCreatedMessage() throws Throwable {

    when(service.readOne(any())).thenReturn(Optional.of(newMessageTemplateEntity("1",
        "Hey, this child has been created on ${child.created} and was modified on ${child.modified}.",
        "TestTemplate1")));

    var map = Map.of("child.created", "03.12.2007 10:15", "child.modified", "08.12.2007 10:17");

    var language = new LanguageEntity();
    language.setLocale("en");
    language.setName("english");

    var result = databaseMessageTemplateService.createMessage("TestTemplate1", map, language);

    assertThat(result).isEqualTo(
        "Hey, this child has been created on 03.12.2007 10:15 and was modified on 08.12.2007 10:17.");
  }

  @Test
  public void checkMessageWhenInputNoMatches() throws Throwable {

    when(service.readOne(any()))
        .thenReturn(Optional.of(newMessageTemplateEntity("2", "Hello World", "TestTemplate2")));

    var map = Map.of("child.created", "03.12.2007 10:15", "child.modified", "08.12.2007 10:17");

    var language = new LanguageEntity();
    language.setLocale("en");
    language.setName("english");

    var result = databaseMessageTemplateService.createMessage("TestTemplate2", map, language);

    assertThat(result).isEqualTo("Hello World");
  }

  @Test
  public void checkMessageWhenInputNull() throws Throwable {

    when(service.readOne(any()))
        .thenReturn(Optional.of(newMessageTemplateEntity("3", null, "TestTemplate3")));

    var map = Map.of("child.created", "03.12.2007 10:15", "child.modified", "08.12.2007 10:17");

    var language = new LanguageEntity();
    language.setLocale("en");
    language.setName("english");

    var result = databaseMessageTemplateService.createMessage("TestTemplate3", map, language);

    assertThat(result).isNull();
  }

  private static MessageTemplateEntity newMessageTemplateEntity(String id, String content,
      String Name) {
    var messageTemplate = new MessageTemplateEntity();
    messageTemplate.setId(id);
    messageTemplate.setName(Name);
    return messageTemplate;
  }
}
