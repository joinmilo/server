package app.wooportal.server.core.media.base;

import java.io.Serial;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import app.wooportal.server.base.userContexts.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.articles.base.ArticleEntity;
import app.wooportal.server.features.contests.contestParticipations.ContestParticipationEntity;
import app.wooportal.server.features.events.base.EventEntity;
import app.wooportal.server.features.organisations.base.OrganisationEntity;
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
@Table(name = "media")
@Service
public class MediaEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @Transient
  @JsonSerialize
  @JsonDeserialize
  private String base64;

  @Column(name = "mime_type", nullable = false)
  private String mimeType;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "cardImage", fetch = FetchType.LAZY)
  private Set<EventEntity> eventCards;

  @OneToMany(mappedBy = "mediaSubmissions", fetch = FetchType.LAZY)
  private Set<ContestParticipationEntity> contestParticipations;

  @OneToMany(mappedBy = "titleImage", fetch = FetchType.LAZY)
  private Set<EventEntity> eventTitleImages;

  @OneToMany(mappedBy = "profilePicture", fetch = FetchType.LAZY)
  private Set<UserContextEntity> userProfilePicture;

  @OneToMany(mappedBy = "titleImage", fetch = FetchType.LAZY)
  private Set<UserContextEntity> userTitleImages;

  @OneToMany(mappedBy = "cardImage", fetch = FetchType.LAZY)
  private Set<OrganisationEntity> organisationCardImage;

  @OneToMany(mappedBy = "titleImage", fetch = FetchType.LAZY)
  private Set<OrganisationEntity> organisationTitleImages;

  @OneToMany(mappedBy = "cardImage", fetch = FetchType.LAZY)
  private Set<ArticleEntity> articleCardImage;

  @OneToMany(mappedBy = "titleImage", fetch = FetchType.LAZY)
  private Set<ArticleEntity> articleTitleImages;
}
