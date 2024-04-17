package app.milo.server.base.report.type.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.base.report.type.ReportTypeEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "report_type_translatables")
public class ReportTypeTranslatableEntity extends TranslatableEntity<ReportTypeEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;
  
}
