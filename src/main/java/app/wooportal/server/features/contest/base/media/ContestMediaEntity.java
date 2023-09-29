package app.wooportal.server.features.contest.base.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.contest.base.ContestEntity;
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
@Table(name = "contest_media", uniqueConstraints = 
@UniqueConstraint(columnNames = { "media_id", "contest_id" }))
public class ContestMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @JoinColumn(nullable = false)
  private Boolean card;
  
  @JoinColumn(nullable = false)
  private Boolean title;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ContestEntity contest;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private MediaEntity media;
  
}
