package app.milo.server.core.messaging.templates;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.messaging.definitions.MessageDefinitionEntity;
import app.milo.server.core.messaging.templates.translations.MessageTemplateTranslatableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "message_templates")
public class MessageTemplateEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "template")
  private Set<MessageDefinitionEntity> definitions;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<MessageTemplateTranslatableEntity> translatables;

}
