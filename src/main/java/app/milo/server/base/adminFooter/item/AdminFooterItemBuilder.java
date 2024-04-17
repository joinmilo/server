package app.milo.server.base.adminFooter.item;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class AdminFooterItemBuilder
    extends PredicateBuilder<QAdminFooterItemEntity, AdminFooterItemEntity> {

  public AdminFooterItemBuilder() {
    super(QAdminFooterItemEntity.adminFooterItemEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }
}
