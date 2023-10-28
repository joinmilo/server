package app.wooportal.server.test.units.core.setup.entities.base;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class TestPredicateBuilder extends PredicateBuilder<QTestEntity, TestEntity>{

  public TestPredicateBuilder() {
    super(QTestEntity.testEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

}
