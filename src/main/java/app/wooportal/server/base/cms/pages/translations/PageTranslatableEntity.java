package app.wooportal.server.base.cms.pages.translations;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.cms.pages.PageEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
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
@Table(name = "page_translatables")
public class PageTranslatableEntity extends TranslatableEntity<PageEntity> {

  private static final long serialVersionUID = 1L;
  
  private String callText;
  
  private String content;
  
  private String name;
  
  private String shortDescription;
  
}
