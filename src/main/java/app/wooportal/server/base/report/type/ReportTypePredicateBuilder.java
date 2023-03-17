package app.wooportal.server.base.report.type;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ReportTypePredicateBuilder
    extends PredicateBuilder<QReportTypeEntity, ReportTypeEntity> {

  public ReportTypePredicateBuilder() {
    super(QReportTypeEntity.reportTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.translatables.any().name.likeIgnoreCase(term);
  }
}
