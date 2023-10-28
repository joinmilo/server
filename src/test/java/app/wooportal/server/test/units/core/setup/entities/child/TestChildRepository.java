package app.wooportal.server.test.units.core.setup.entities.child;

import org.springframework.data.repository.NoRepositoryBean;

import app.wooportal.server.core.repository.DataRepository;

@NoRepositoryBean
public interface TestChildRepository extends DataRepository<TestChildEntity> {

}
