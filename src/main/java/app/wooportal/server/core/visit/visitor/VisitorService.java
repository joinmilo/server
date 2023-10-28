package app.wooportal.server.core.visit.visitor;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class VisitorService extends DataService<VisitorEntity, VisitorPredicateBuilder> {
  
  public VisitorService(
      DataRepository<VisitorEntity> repo, 
      VisitorPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  @Override
  public Optional<VisitorEntity> getExisting(VisitorEntity newEntity) {
    return newEntity.getIpAddress() != null
        ? repo.findOne(singleQuery(predicate.withIpAddress(newEntity.getIpAddress())))
        : Optional.empty();
  }
  
  public void emptyIdAddresses() {
    var results = repo.findAll(collectionQuery(predicate.withIpAddressNotEmpty()));
    if (results != null && !results.isEmpty()) {
      repo.saveAll(results.getList().stream().map(visitor -> {
        visitor.setIpAddress(null);
        return visitor;
      }).collect(Collectors.toList()));
    }
  }

}
