package app.wooportal.server.features.surveys.answers;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.surveys.questionOptions.QuestionOptionEntity;
import app.wooportal.server.features.surveys.questions.QuestionEntity;
import app.wooportal.server.features.surveys.surveyResult.SurveyResultEntity;
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
@Table(name = "answers")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class AnswerEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private SurveyResultEntity result;

  @ManyToOne(fetch = FetchType.LAZY)
  private QuestionEntity question;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "answer_question_options", joinColumns = @JoinColumn(name = "answer_id"),
      inverseJoinColumns = @JoinColumn(name = "question_option_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"answer_id", "question_option_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<QuestionOptionEntity> selectedOptions = new ArrayList<>();
  
  
}
