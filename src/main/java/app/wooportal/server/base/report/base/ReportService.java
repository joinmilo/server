package app.wooportal.server.base.report.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.hcaptcha.CaptchaService;
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

    if (entity.getCaptcha() != null && !entity.getCaptcha().isEmpty()) {

      captchaService.verifyToken(entity.getCaptcha());
    }
  }
}


