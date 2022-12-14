package app.wooportal.server.base.userContexts.friend;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "friends")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class FriendEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean accepted;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity addressee;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity requester;
  
  
}
