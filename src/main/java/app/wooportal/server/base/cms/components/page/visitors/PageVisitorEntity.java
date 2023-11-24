package app.wooportal.server.base.cms.components.page.visitors;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.cms.components.page.PageEntity;
import app.wooportal.server.core.visit.visitable.VisitableEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "page_visitors")
public class PageVisitorEntity extends VisitableEntity<PageEntity> {

  private static final long serialVersionUID = 1L;
}
