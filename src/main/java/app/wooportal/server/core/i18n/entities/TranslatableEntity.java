package app.wooportal.server.core.i18n.entities;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;

@MappedSuperclass
public abstract class TranslatableEntity<P extends BaseEntity> extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonIgnore
  protected LanguageEntity language;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name = "parent_id", nullable = false)
  protected P parent;

  public LanguageEntity getLanguage() {
    return this.language;
  }

  public void setLanguage(LanguageEntity language) {
    this.language = language;
  }

  public void setParent(P parent) {
    this.parent = parent;
  }

}
