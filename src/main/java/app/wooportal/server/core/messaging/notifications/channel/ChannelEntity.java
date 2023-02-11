package app.wooportal.server.core.messaging.notifications.channel;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.messaging.definitions.MessageDefinitionEntity;
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
@Table(name = "channels")
@GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
)
public class ChannelEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(unique = true, nullable = false)
  private String name;
  
  @Column(unique = true, nullable = false)
  private String key;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "message_definition_channels",
      joinColumns = @JoinColumn(name = "channel_id"),
      inverseJoinColumns = @JoinColumn(name = "message_definition_id"),
      uniqueConstraints = { 
          @UniqueConstraint(columnNames = {"message_definition_id", "channel_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MessageDefinitionEntity> messageDefinitions;

}
