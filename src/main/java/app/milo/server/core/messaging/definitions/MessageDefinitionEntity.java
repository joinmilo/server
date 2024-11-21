package app.milo.server.core.messaging.definitions;

import java.util.List;
import java.util.Set;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.messaging.channels.ChannelEntity;
import app.milo.server.core.messaging.definitions.translations.MessageDefinitionTranslatableEntity;
import app.milo.server.core.messaging.templates.MessageTemplateEntity;
import app.milo.server.core.security.components.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "message_definitions")
public class MessageDefinitionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "message_template_id")
  private MessageTemplateEntity template;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<MessageDefinitionTranslatableEntity> translatables;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "message_definition_channels",
      joinColumns = @JoinColumn(name = "message_definition_id"),
      inverseJoinColumns = @JoinColumn(name = "channel_id"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"message_definition_id", "channel_id"})})
  private List<ChannelEntity> channels;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "message_definition_users",
      joinColumns = @JoinColumn(name = "message_definition_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"message_definition_id", "user_id"})})
  private List<UserEntity> users;
}
