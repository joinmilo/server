package app.milo.server.core.visit.visitable;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.visit.visitor.VisitorEntity;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public abstract class VisitableEntity<E extends BaseEntity> extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  protected Integer visits;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(nullable = false)
  protected VisitorEntity visitor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name = "parent_id", nullable = false)
  protected E parent;

  @SuppressWarnings("unchecked")
  public void setParent(BaseEntity parent) {
    this.parent = (E) parent;
  }

  public void addVisit() {
    this.visits = this.visits != null
        ? this.visits + 1
        : 1;
  }
}

