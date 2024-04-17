package app.milo.server.test.units.core.setup.entities.listChild;

import org.springframework.data.repository.NoRepositoryBean;
import app.milo.server.core.repository.DataRepository;

@NoRepositoryBean
public interface TestListChildRepository extends DataRepository<TestListChildEntity> {

}
