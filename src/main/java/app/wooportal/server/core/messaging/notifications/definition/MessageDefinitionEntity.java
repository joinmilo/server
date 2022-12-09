package app.wooportal.server.core.messaging.notifications.definition;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.messaging.notifications.channel.ChannelEntity;
import app.wooportal.server.core.messaging.notifications.template.MessageTemplateEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
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

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "message_definition_channels",
      joinColumns = @JoinColumn(name = "message_definition_id"),
      inverseJoinColumns = @JoinColumn(name = "channel_id"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"message_definition_id", "channel_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<ChannelEntity> channels;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "message_template_id")
  private MessageTemplateEntity template;
}
