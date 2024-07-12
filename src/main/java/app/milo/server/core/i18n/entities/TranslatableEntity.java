package app.milo.server.core.i18n.entities;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.components.language.LanguageEntity;

@MappedSuperclass
public abstract class TranslatableEntity<P extends BaseEntity> extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(nullable = false)
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
