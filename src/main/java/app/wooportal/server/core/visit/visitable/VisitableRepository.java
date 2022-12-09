package app.wooportal.server.core.visit.visitable;

import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.visit.visitor.VisitorEntity;

@NoRepositoryBean
public interface VisitableRepository<T extends VisitableEntity<?>> extends DataRepository<T> {

  <E extends BaseEntity> T findByParentAndVisitor(E parent, VisitorEntity visitor);

  <E extends BaseEntity> List<T> findByParent(E parent);
}
