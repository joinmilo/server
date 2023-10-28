package app.wooportal.server.core.visit.visitor;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class VisitorPredicateBuilder extends PredicateBuilder<QVisitorEntity, VisitorEntity> {

  public VisitorPredicateBuilder() {
    super(QVisitorEntity.visitorEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.userAgent.likeIgnoreCase(term)
        .or(query.ipAddress.likeIgnoreCase(term));
  }
  
  public BooleanExpression withIpAddress(String ipAddress) {
    return query.ipAddress.eq(ipAddress);
  }
  
  public BooleanExpression withUserAgent(String userAgent) {
    return query.userAgent.eq(userAgent);
  }

  public BooleanExpression withIpAddressNotEmpty() {
    return query.ipAddress.isNotNull().and(query.ipAddress.isNotEmpty());
  }

}
