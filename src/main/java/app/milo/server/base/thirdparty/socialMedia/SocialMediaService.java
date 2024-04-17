package app.milo.server.base.thirdparty.socialMedia;

import org.springframework.stereotype.Service;
import app.milo.server.core.base.DataService;
import app.milo.server.core.repository.DataRepository;

@Service
public class SocialMediaService extends DataService<SocialMediaEntity, SocialMediaPredicateBuilder> {

  public SocialMediaService(DataRepository<SocialMediaEntity> repo, SocialMediaPredicateBuilder predicate) {
    super(repo, predicate);
  }
}
