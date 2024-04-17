package app.milo.server.base.report.base.media;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class ReportMediaPredicateBuilder
    extends PredicateBuilder<QReportMediaEntity, ReportMediaEntity> {

  public ReportMediaPredicateBuilder() {
    super(QReportMediaEntity.reportMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.media.name.likeIgnoreCase(term);
  }
}
