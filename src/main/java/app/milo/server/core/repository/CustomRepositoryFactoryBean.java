package app.milo.server.core.repository;

import jakarta.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CustomRepositoryFactoryBean<T extends Repository<S, I>, S, I>
    extends JpaRepositoryFactoryBean<T, S, I> {

  public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
    super(repositoryInterface);
  }

  protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new CustomRepositoryFactory(entityManager);
  }

}
