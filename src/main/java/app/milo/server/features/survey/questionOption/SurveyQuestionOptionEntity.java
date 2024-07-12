package app.milo.server.features.survey.questionOption;

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
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.survey.question.SurveyQuestionEntity;
import app.milo.server.features.survey.questionOption.translations.SurveyQuestionOptionTranslatableEntity;
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
@Table(name = "survey_question_options")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class SurveyQuestionOptionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String label;

  @Column(nullable = false)
  private Integer order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyQuestionEntity question;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<SurveyQuestionOptionTranslatableEntity> translatables;
}
