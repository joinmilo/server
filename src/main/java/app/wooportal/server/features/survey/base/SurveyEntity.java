package app.wooportal.server.features.survey.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.features.survey.assignment.AssignmentEntity;
import app.wooportal.server.features.survey.base.media.SurveyMediaEntity;
import app.wooportal.server.features.survey.base.translations.SurveyTranslatableEntity;
import app.wooportal.server.features.survey.base.visitors.SurveyVisitorEntity;
import app.wooportal.server.features.survey.state.SurveyStateEntity;
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
@Table(name = "surveys")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class SurveyEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String description; 

  private Date dueDate;

  @Column(nullable = false)
  private Boolean mandatory;
  
  @Translatable
  private String name;

  private String metaDescription;

  private String slug;
  @Column(nullable = false)
  private Boolean sponsored;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyStateEntity state;

  @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
  private Set<AssignmentEntity> assignments;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyVisitorEntity> visitors;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyTranslatableEntity> translatables;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "survey")
  private List<SurveyMediaEntity> uploads = new ArrayList<>();
}
