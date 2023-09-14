package app.wooportal.server.core.visit.visitable;

import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.repository.DataRepository;

@NoRepositoryBean
public interface VisitableRepository<T extends VisitableEntity<?>> extends DataRepository<T> {

  <E extends BaseEntity> T findByParentIdAndVisitorId(String parentId, String visitorId);

  <E extends BaseEntity> List<T> findByParentId(String parentId);
}
