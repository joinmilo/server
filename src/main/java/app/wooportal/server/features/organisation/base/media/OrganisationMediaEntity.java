package app.wooportal.server.features.organisation.base.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.features.organisation.base.OrganisationEntity;
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
@Table(name = "organisation_media")
public class OrganisationMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean title;
  
  private Boolean card;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationEntity organisation;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
  

}