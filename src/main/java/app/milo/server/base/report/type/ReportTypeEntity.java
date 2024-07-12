package app.milo.server.base.report.type;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.base.report.base.ReportEntity;
import app.milo.server.base.report.type.translations.ReportTypeTranslatableEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
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
  
  @Translatable
  private String name;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<ReportTypeTranslatableEntity> translatables;

  @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
  private Set<ReportEntity> reports;


}
