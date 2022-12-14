package app.wooportal.server.features.surveys.base;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.surveys.assignments.AssignmentEntity;
import app.wooportal.server.features.surveys.surveyVisitors.SurveyVisitorEntity;
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

  @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
  private Set<AssignmentEntity> assignments;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyVisitorEntity> surveyVisitors;

  private String seoDescription;

  private String slug;

  private Date due_date;

  private Boolean mandatory;


}
