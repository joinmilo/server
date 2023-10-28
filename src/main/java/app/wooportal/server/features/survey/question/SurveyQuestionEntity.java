package app.wooportal.server.features.survey.question;

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
import app.wooportal.server.features.survey.answer.SurveyAnswerEntity;
import app.wooportal.server.features.survey.question.translations.SurveyQuestionTranslatableEntity;
import app.wooportal.server.features.survey.questionOption.SurveyQuestionOptionEntity;
import app.wooportal.server.features.survey.questionType.SurveyQuestionTypeEntity;
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
@Table(name = "survey_questions")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class SurveyQuestionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column
  private String subject;

  @Column(nullable = false)
  private Integer order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyQuestionTypeEntity type;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  private Set<SurveyAnswerEntity> answers;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  private Set<SurveyQuestionOptionEntity> questionOptions;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyQuestionTranslatableEntity> translatables;
}
