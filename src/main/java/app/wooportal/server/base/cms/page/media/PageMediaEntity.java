package app.wooportal.server.base.cms.page.media;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.base.cms.page.PageEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
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
@Table(name = "page_media")
public class PageMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  private Boolean title;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private PageEntity page;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;
}
