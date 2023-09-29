package app.wooportal.server.base.report.base.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.base.report.base.ReportEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "report_media", uniqueConstraints = 
@UniqueConstraint(columnNames = { "media_id", "report_id" }))
public class ReportMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ReportEntity report;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private MediaEntity media;
}
