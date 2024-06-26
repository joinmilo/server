package app.milo.server.base.thirdparty.socialMedia;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.milo.server.core.base.PredicateBuilder;

@Service
public class SocialMediaPredicateBuilder
    extends PredicateBuilder<QSocialMediaEntity, SocialMediaEntity> {

  public SocialMediaPredicateBuilder() {
    super(QSocialMediaEntity.socialMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
