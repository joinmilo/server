package app.milo.server.features.survey.answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.survey.answer.translations.SurveyAnswerTranslatableEntity;
import app.milo.server.features.survey.question.SurveyQuestionEntity;
import app.milo.server.features.survey.questionOption.SurveyQuestionOptionEntity;
import app.milo.server.features.survey.result.SurveyResultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "survey_answers")
public class SurveyAnswerEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyResultEntity result;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyQuestionEntity question;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "answer_question_options", joinColumns = @JoinColumn(name = "answer_id"),
      inverseJoinColumns = @JoinColumn(name = "question_option_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"answer_id", "question_option_id"})})
  
  private List<SurveyQuestionOptionEntity> selectedOptions = new ArrayList<>();

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyAnswerTranslatableEntity> translatables;
}
