package app.wooportal.server.base.milestone.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.base.milestone.MilestoneEntity;
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
@Table(name = "milestone_media")
public class MilestoneMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MilestoneEntity milestone;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
  
}
