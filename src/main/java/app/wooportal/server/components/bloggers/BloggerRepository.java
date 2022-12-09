package app.wooportal.server.components.bloggers;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends DataRepository<BloggerEntity> {

}
