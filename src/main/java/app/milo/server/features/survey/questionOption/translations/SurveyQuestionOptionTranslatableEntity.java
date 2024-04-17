package app.milo.server.features.survey.questionOption.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.survey.questionOption.SurveyQuestionOptionEntity;
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
@Table(name = "question_option_translatables")
public class SurveyQuestionOptionTranslatableEntity extends TranslatableEntity<SurveyQuestionOptionEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String label;

}
