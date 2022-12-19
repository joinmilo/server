package app.wooportal.server.features.surveys.assignments;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.surveys.base.SurveyEntity;
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
@Table(name = "assignments")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class AssignmentEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyEntity survey;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserContextEntity userContext;

}
