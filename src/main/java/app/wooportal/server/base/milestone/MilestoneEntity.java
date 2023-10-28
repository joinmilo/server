package app.wooportal.server.base.milestone;

import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import app.wooportal.server.base.milestone.element.MilestoneElementEntity;
import app.wooportal.server.base.milestone.media.MilestoneMediaEntity;
import app.wooportal.server.base.milestone.translations.MilestoneTranslatableEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
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
@Table(name = "milestones")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class MilestoneEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String name;
  
  @Column(nullable = false)
  private OffsetDateTime endDate;
  
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<MilestoneTranslatableEntity> translatables;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "milestone")
  private Set<MilestoneMediaEntity> uploads;
  
  @OneToMany(mappedBy = "milestone", fetch = FetchType.LAZY)
  private Set<MilestoneElementEntity> elements;  

}
