package app.wooportal.server.base.thirdparty.socialMedia;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SocialMediaService extends DataService<SocialMediaEntity, SocialMediaPredicateBuilder> {

  public SocialMediaService(DataRepository<SocialMediaEntity> repo, SocialMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
