package app.milo.server.base.cms.components.page.base.visitors;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.base.cms.components.page.base.PageEntity;
import app.milo.server.core.visit.visitable.VisitableEntity;
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
