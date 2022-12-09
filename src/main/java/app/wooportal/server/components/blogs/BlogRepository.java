package app.wooportal.server.components.blogs;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends DataRepository<BlogEntity> {

}
