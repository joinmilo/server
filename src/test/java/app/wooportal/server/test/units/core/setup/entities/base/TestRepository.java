package app.wooportal.server.test.units.core.setup.entities.base;

import org.springframework.data.repository.NoRepositoryBean;

import app.wooportal.server.core.repository.DataRepository;

@NoRepositoryBean
public interface TestRepository extends DataRepository<TestEntity> {

}
