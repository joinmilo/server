package app.wooportal.server.base.inquiry.category;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class InquiryCategoryService extends DataService<InquiryCategoryEntity, InquiryCategoryPredicateBuilder> {

  public InquiryCategoryService(DataRepository<InquiryCategoryEntity> repo,
      InquiryCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
