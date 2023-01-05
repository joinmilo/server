package app.wooportal.server.core.messaging.templates.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.core.messaging.templates.MessageTemplateEntity;
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
