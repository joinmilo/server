package app.wooportal.server.base.cms.translations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.base.cms.pages.PageEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.core.i18n.language.entities.TranslatableEntity;
import app.wooportal.server.features.events.base.EventEntity;
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
public class PageTranslatableEntity extends TranslatableEntity<EventEntity> {

  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false)
  private String content;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private LanguageEntity language;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private PageEntity parent;
}
