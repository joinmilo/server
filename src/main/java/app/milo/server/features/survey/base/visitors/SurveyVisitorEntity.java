package app.milo.server.features.survey.base.visitors;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.visit.visitable.VisitableEntity;
import app.milo.server.features.survey.base.SurveyEntity;
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
