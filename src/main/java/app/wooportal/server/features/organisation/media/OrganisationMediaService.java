package app.wooportal.server.features.organisation.media;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;


@Service
public class OrganisationMediaService
    extends DataService<OrganisationMediaEntity, OrganisationMediaPredicateBuilder> {

  public OrganisationMediaService(DataRepository<OrganisationMediaEntity> repo,
      OrganisationMediaPredicateBuilder predicate,
      MediaService mediaService) {
    super(repo, predicate);
    
    addService("media", mediaService);
  }
  
  @Override
  public Optional<OrganisationMediaEntity> getExisting(OrganisationMediaEntity entity) {
    return entity != null
        && entity.getMedia() != null
        && entity.getMedia().getId() != null
        && entity.getOrganisation() != null
        && entity.getOrganisation().getId() != null
       ? repo.findOne(singleQuery(predicate.withMedia(entity.getMedia().getId()))
           .and(predicate.withOrganisation(entity.getOrganisation().getId()))
         )
       : Optional.empty();
  }
}
