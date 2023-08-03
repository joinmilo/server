package app.wooportal.server.features.organisation.comment;

import java.util.Optional;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.SortPageUtils;

@Service
public class OrganisationCommentService extends DataService<OrganisationCommentEntity, OrganisationCommentPredicateBuilder> {

  public OrganisationCommentService(DataRepository<OrganisationCommentEntity> repo, OrganisationCommentPredicateBuilder predicate) {
    super(repo, predicate);
  }
  public Optional<OrganisationCommentEntity> getMostRecentByOrganisation(String eventId) {
    var result = repo.findAll(
        collectionQuery(predicate.withOrganisationId(eventId))
          .setLimit(1)
          .setSort(SortPageUtils.createSort(Direction.DESC, "modified")));
    
    return result != null && !result.isEmpty()
        ? Optional.ofNullable(result.get(0))
        : Optional.empty();
  }
}
