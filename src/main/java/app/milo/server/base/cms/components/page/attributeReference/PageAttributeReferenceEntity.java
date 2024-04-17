package app.milo.server.base.cms.components.page.attributeReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.milo.server.base.cms.components.page.attribute.PageAttributeEntity;
import app.milo.server.base.cms.components.plugin.PluginEntity;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.media.base.MediaEntity;
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
@Table(name = "page_attribute_references")
public class PageAttributeReferenceEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  protected PageAttributeEntity attribute;
  
  @ManyToOne(fetch = FetchType.LAZY)
  protected MediaEntity media;

  @ManyToOne(fetch = FetchType.LAZY)
  protected PluginEntity plugin;
  

}
