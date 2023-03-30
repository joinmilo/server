package app.wooportal.server.base.report.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.captcha.CaptchaService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ReportService extends DataService<ReportEntity, ReportPredicateBuilder> {


  private final CaptchaService captchaService;


  public ReportService(DataRepository<ReportEntity> repo, ReportPredicateBuilder predicate,
      CaptchaService captchaService) {
    super(repo, predicate);

    this.captchaService = captchaService;
  }

  @Override
  public void preSave(ReportEntity entity, ReportEntity newEntity, JsonNode context) {

    //TODO: Make an hook for specific validations
    if (entity.getCaptchaToken() != null && !entity.getCaptchaToken().isEmpty()) {
      captchaService.verifyToken(entity.getCaptchaToken());
    } else {
      throw new BadParamsException("Captcha token empty or null", null);
    }
  }
}


