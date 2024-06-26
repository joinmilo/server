package app.milo.server.core.visit.visitable;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.repository.DataRepository;

@NoRepositoryBean
public interface VisitableRepository<T extends VisitableEntity<?>> extends DataRepository<T> {

  <E extends BaseEntity> T findByParentIdAndVisitorId(String parentId, String visitorId);

  <E extends BaseEntity> List<T> findByParentIdAndCreatedBetween(String parentId, OffsetDateTime start, OffsetDateTime end);
}
