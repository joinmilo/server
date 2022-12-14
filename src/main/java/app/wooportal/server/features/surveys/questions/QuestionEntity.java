package app.wooportal.server.features.surveys.questions;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.surveys.answers.AnswerEntity;
import app.wooportal.server.features.surveys.questionOptions.QuestionOptionEntity;
import app.wooportal.server.features.surveys.questionType.QuestionTypeEntity;
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
@Table(name = "questions")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class QuestionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private SurveyResultEntity result;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  private QuestionOptionEntity questionOptions;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  private Set<AnswerEntity> answers;

  @ManyToOne(fetch = FetchType.LAZY)
  private QuestionTypeEntity type;

}
