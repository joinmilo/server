package app.milo.server.core.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition.RepositoryFragments;
import org.springframework.data.repository.core.support.RepositoryFragment;

public class CustomRepositoryFactory extends JpaRepositoryFactory {
  
  private final EntityManager entityManager;

  public CustomRepositoryFactory(EntityManager entityManager) {
    super(entityManager);
    this.entityManager = entityManager;
  }
 
  @Override
  protected RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {
    var fragments = super.getRepositoryFragments(metadata);

    if (QueryableReadRepository.class.isAssignableFrom(
        metadata.getRepositoryInterface())) {

      var entityInformation = 
          getEntityInformation(metadata.getDomainType());

      var queryableFragment = instantiateClass(
          QueryableReadRepositoryImpl.class, entityInformation, entityManager);

      fragments = fragments.append(RepositoryFragment.implemented(queryableFragment));
    }

    return fragments;
  }

}
