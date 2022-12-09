package app.wooportal.server.core.visit.visitor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
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
