package app.wooportal.server.base.feedback.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.base.feedback.translations.FeedbackTranslatableEntity;
import app.wooportal.server.base.feedback.type.FeedbackTypeEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
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
@Table(name = "feedbacks")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class FeedbackEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String email;
  
  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<FeedbackTranslatableEntity> translatables;

  @ManyToOne(fetch = FetchType.LAZY)
  private FeedbackTypeEntity type;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "feedback_media", joinColumns = @JoinColumn(name = "feedback_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"feedback_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> media = new ArrayList<>();
}
