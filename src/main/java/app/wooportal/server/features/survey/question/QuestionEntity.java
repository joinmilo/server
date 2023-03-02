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
import app.wooportal.server.features.survey.answer.AnswerEntity;
import app.wooportal.server.features.survey.question.translations.QuestionTranslatableEntity;
import app.wooportal.server.features.survey.questionOption.QuestionOptionEntity;
import app.wooportal.server.features.survey.questionType.QuestionTypeEntity;
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

  @Column(nullable = false)
  private Integer order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private QuestionTypeEntity type;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  private Set<AnswerEntity> answers;

  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
  private Set<QuestionOptionEntity> questionOptions;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<QuestionTranslatableEntity> translatables;
}
