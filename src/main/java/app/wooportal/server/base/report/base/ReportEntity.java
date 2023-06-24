package app.wooportal.server.base.report.base;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.report.base.media.ReportMediaEntity;
import app.wooportal.server.base.report.base.translations.ReportTranslatableEntity;
import app.wooportal.server.base.report.type.ReportTypeEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
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
@Table(name = "reports")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ReportEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Transient
  private String captchaToken;

  @Translatable
  private String content;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<ReportTranslatableEntity> translatables;

  @ManyToOne(fetch = FetchType.LAZY)
  private ReportTypeEntity type;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "report")
  private Set<ReportMediaEntity> uploads;
}
