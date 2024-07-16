package app.milo.server.core.visit.visitor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "visitors")
public class VisitorEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = true, unique = true)
  private String ipAddress;

  @Column(nullable = false)
  private String userAgent;

}
