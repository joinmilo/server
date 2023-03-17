package app.wooportal.server.base.report.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ReportPredicateBuilder extends PredicateBuilder<QReportEntity, ReportEntity> {

  public ReportPredicateBuilder() {
    super(QReportEntity.reportEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.type.id.likeIgnoreCase(term);
  }
}
