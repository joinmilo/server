package app.wooportal.server.features.surveys.surveyResult.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.features.surveys.surveyResult.SurveyResultEntity;
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
@Table(name = "survey_result_translatables")
public class SurveyResultTranslatableEntity extends TranslatableEntity<SurveyResultEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyResultEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private LanguageEntity language;
}
