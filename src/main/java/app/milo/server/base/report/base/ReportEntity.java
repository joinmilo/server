package app.milo.server.base.report.base;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.base.report.base.media.ReportMediaEntity;
import app.milo.server.base.report.base.translations.ReportTranslatableEntity;
import app.milo.server.base.report.type.ReportTypeEntity;
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
