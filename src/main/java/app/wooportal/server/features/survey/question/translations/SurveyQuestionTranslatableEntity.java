package app.wooportal.server.features.survey.question.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.features.survey.question.SurveyQuestionEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "survey_question_option_translatables")
public class SurveyQuestionTranslatableEntity extends TranslatableEntity<SurveyQuestionEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String subject;

}
