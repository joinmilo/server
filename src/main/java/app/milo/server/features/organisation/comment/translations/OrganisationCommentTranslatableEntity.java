package app.milo.server.features.organisation.comment.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.organisation.comment.OrganisationCommentEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "organisation_comment_translatables")
public class OrganisationCommentTranslatableEntity extends TranslatableEntity<OrganisationCommentEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;

}
