package app.milo.server.test.units.core.setup.entities.child;

import org.springframework.data.repository.NoRepositoryBean;
import app.milo.server.core.repository.DataRepository;

@NoRepositoryBean
public interface TestChildRepository extends DataRepository<TestChildEntity> {

}
