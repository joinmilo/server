package app.milo.server.base.userContext.base.media;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import app.milo.server.base.userContext.base.UserContextEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.media.base.MediaEntity;
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
@Table(name = "user_context_media", uniqueConstraints = 
@UniqueConstraint(columnNames = { "media_id", "user_context_id" }))
public class UserContextMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean title = false;
  
  private Boolean profilePicture = false;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
  

}
