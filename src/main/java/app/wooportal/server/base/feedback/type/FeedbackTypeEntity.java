package app.wooportal.server.base.feedback.type;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.feedback.base.FeedbackEntity;
import app.wooportal.server.base.feedback.type.translations.FeedbackTypeTranslatableEntity;
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
@Table(name = "feedback_types")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class FeedbackTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<FeedbackTypeTranslatableEntity> translatables;

  @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
  private Set<FeedbackEntity> feedbacks;


}
