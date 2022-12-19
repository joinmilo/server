package app.wooportal.server.core.visit.visitor;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.events.eventVisitors.EventVisitorEntity;
import app.wooportal.server.features.organisations.organisationVisitor.OrganisationVisitorEntity;
import app.wooportal.server.features.surveys.surveyVisitors.SurveyVisitorEntity;
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

  @OneToMany(mappedBy = "visitor", fetch = FetchType.LAZY)
  private Set<EventVisitorEntity> eventVisitor;

  @OneToMany(mappedBy = "visitor", fetch = FetchType.LAZY)
  private Set<OrganisationVisitorEntity> organisationVisitor;


  @OneToMany(mappedBy = "visitor", fetch = FetchType.LAZY)
  private Set<SurveyVisitorEntity> surveyVisitor;
}
