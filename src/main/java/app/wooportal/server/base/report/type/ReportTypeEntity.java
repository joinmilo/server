package app.wooportal.server.base.report.type;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.report.base.ReportEntity;
import app.wooportal.server.base.report.type.translations.ReportTypeTranslatableEntity;
import app.wooportal.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "report_types")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ReportTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<ReportTypeTranslatableEntity> translatables;

  @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
  private Set<ReportEntity> reports;


}
