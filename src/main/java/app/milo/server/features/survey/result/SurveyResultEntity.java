package app.milo.server.features.survey.result;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.base.userContext.base.UserContextEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.survey.answer.SurveyAnswerEntity;
import app.milo.server.features.survey.base.SurveyEntity;
import app.milo.server.features.survey.result.translations.SurveyResultTranslatableEntity;
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
  
  @Translatable
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyEntity survey;

  @OneToMany(mappedBy = "result", fetch = FetchType.LAZY)
  private Set<SurveyAnswerEntity> answer;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyResultTranslatableEntity> translatables;
}
