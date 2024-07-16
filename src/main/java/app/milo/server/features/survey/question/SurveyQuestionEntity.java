package app.milo.server.features.survey.question;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.features.survey.answer.SurveyAnswerEntity;
import app.milo.server.features.survey.question.translations.SurveyQuestionTranslatableEntity;
import app.milo.server.features.survey.questionOption.SurveyQuestionOptionEntity;
import app.milo.server.features.survey.questionType.SurveyQuestionTypeEntity;
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
