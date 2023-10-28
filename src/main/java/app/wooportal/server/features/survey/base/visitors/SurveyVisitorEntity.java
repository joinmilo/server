package app.wooportal.server.features.survey.base.visitors;

import javax.persistence.Entity;
import javax.persistence.Table;

import app.wooportal.server.core.visit.visitable.VisitableEntity;
import app.wooportal.server.features.survey.base.SurveyEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "survey_visitors")
public class SurveyVisitorEntity extends VisitableEntity<SurveyEntity> {

  private static final long serialVersionUID = 1L;

}
