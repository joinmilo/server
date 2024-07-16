package app.milo.server.features.organisation.media;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.media.base.MediaEntity;
import app.milo.server.features.organisation.base.OrganisationEntity;
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
@Table(name = "organisation_media", uniqueConstraints = 
@UniqueConstraint(columnNames = { "media_id", "organisation_id" }))
public class OrganisationMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean title = false;
  
  private Boolean card = false;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationEntity organisation;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
}
