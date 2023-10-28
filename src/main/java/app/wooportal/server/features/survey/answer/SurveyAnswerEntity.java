package app.wooportal.server.features.survey.answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.features.survey.answer.translations.SurveyAnswerTranslatableEntity;
import app.wooportal.server.features.survey.question.SurveyQuestionEntity;
import app.wooportal.server.features.survey.questionOption.SurveyQuestionOptionEntity;
import app.wooportal.server.features.survey.result.SurveyResultEntity;
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
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
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
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<SurveyQuestionOptionEntity> selectedOptions = new ArrayList<>();

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyAnswerTranslatableEntity> translatables;
}
