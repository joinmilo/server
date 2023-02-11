package app.wooportal.server.base.inquiry.category;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class InquiryCategoryPredicateBuilder
    extends PredicateBuilder<QInquiryCategoryEntity, InquiryCategoryEntity> {

  public InquiryCategoryPredicateBuilder() {
    super(QInquiryCategoryEntity.inquiryCategoryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
