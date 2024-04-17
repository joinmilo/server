package app.milo.server.base.cms.components.page.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
public interface PageRepository extends DataRepository<PageEntity> {

}
