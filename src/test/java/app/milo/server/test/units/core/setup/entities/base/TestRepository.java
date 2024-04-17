package app.milo.server.test.units.core.setup.entities.base;

import org.springframework.data.repository.NoRepositoryBean;
import app.milo.server.core.repository.DataRepository;

@NoRepositoryBean
public interface TestRepository extends DataRepository<TestEntity> {

}
