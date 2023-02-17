package app.wooportal.server.base.inquiry.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class InquiryPredicateBuilder extends PredicateBuilder<QInquiryEntity, InquiryEntity> {

  public InquiryPredicateBuilder() {
    super(QInquiryEntity.inquiryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
