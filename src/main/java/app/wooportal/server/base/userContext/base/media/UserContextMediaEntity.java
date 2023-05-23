package app.wooportal.server.base.userContext.base.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
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
@Table(name = "user_context_media")
public class UserContextMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean title;
  
  private Boolean profilePicture;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
  

}
