package app.milo.server.base.milestone.element;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.base.milestone.MilestoneEntity;
import app.milo.server.base.milestone.element.translations.MilestoneElementTranslatableEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
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
