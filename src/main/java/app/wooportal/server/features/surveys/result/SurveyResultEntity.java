package app.wooportal.server.features.surveys.result;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.surveys.answers.AnswerEntity;
import app.wooportal.server.features.surveys.base.SurveyEntity;
import app.wooportal.server.features.surveys.result.translations.SurveyResultTranslatableEntity;
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
@Table(name = "survey_results")
public class SurveyResultEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyEntity survey;

  @OneToMany(mappedBy = "result", fetch = FetchType.LAZY)
  private Set<AnswerEntity> answer;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyResultTranslatableEntity> translatables;
}
