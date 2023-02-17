package app.wooportal.server.base.inquiry.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class InquiryService extends DataService<InquiryEntity, InquiryPredicateBuilder> {

  public InquiryService(DataRepository<InquiryEntity> repo,
      InquiryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
