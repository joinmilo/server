package app.wooportal.server.base.milestone.element;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import app.wooportal.server.base.milestone.MilestoneEntity;
import app.wooportal.server.base.milestone.element.translations.MilestoneElementTranslatableEntity;
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
@Table(name = "milestone_elements")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class MilestoneElementEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String name;
  
  @Translatable
  private String description;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MilestoneEntity milestone;
    
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<MilestoneElementTranslatableEntity> translatables;
  


}
