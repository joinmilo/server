package app.wooportal.server.features.surveys.questions.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.features.surveys.questions.QuestionEntity;
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
public class QuestionTranslatableEntity extends TranslatableEntity<QuestionEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String subject;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private QuestionEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private LanguageEntity language;
}
