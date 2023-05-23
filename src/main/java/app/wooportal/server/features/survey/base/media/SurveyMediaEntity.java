package app.wooportal.server.features.survey.base.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.survey.base.SurveyEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "survey_media")
public class SurveyMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean card;

  private Boolean title;

  @ManyToOne(fetch = FetchType.LAZY)
  private SurveyEntity survey;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;

}
