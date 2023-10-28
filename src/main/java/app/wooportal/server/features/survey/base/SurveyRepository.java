package app.wooportal.server.features.survey.base;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface SurveyRepository extends DataRepository<SurveyEntity> {

}
