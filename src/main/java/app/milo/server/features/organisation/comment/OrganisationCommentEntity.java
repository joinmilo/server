package app.milo.server.features.organisation.comment;

import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import app.milo.server.base.userContext.base.UserContextEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.organisation.base.OrganisationEntity;
import app.milo.server.features.organisation.comment.translations.OrganisationCommentTranslatableEntity;
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
@Table(name = "organisation_comments")
public class OrganisationCommentEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationEntity organisation;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<OrganisationCommentTranslatableEntity> translatables;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;
}
