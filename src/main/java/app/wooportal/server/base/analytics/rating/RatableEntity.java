package app.wooportal.server.base.analytics.rating;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class RatableEntity<E extends BaseEntity> extends BaseEntity {
  
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Integer score;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id", nullable = false)
  protected E parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;

}
