package app.milo.server.base.adminFooter.parent;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class AdminFooterParentBuilder
    extends PredicateBuilder<QAdminFooterParentEntity, AdminFooterParentEntity> {

  public AdminFooterParentBuilder() {
    super(QAdminFooterParentEntity.adminFooterParentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
