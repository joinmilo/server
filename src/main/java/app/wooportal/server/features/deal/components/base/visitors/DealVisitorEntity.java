package app.wooportal.server.features.deal.components.base.visitors;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.visit.visitable.VisitableEntity;
import app.wooportal.server.features.deal.components.base.DealEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "deal_visitors")
public class DealVisitorEntity extends VisitableEntity<DealEntity> {

  private static final long serialVersionUID = 1L;

}
