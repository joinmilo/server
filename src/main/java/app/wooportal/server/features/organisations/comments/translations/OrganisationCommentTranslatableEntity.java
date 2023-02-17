package app.wooportal.server.features.organisations.comments.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.features.events.base.EventEntity;
import app.wooportal.server.features.organisations.comments.OrganisationCommentEntity;
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
public class OrganisationCommentTranslatableEntity extends TranslatableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationCommentEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;
}
