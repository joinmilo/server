package app.milo.server.core.messaging.templates.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.components.language.LanguageEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.core.messaging.templates.MessageTemplateEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "message_definition_translatables")
public class MessageTemplateTranslatableEntity extends TranslatableEntity<MessageTemplateEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private MessageTemplateEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;
}
