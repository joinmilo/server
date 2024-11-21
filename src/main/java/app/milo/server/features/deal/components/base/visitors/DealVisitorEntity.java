package app.milo.server.features.deal.components.base.visitors;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.visit.visitable.VisitableEntity;
import app.milo.server.features.deal.components.base.DealEntity;
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
