package app.milo.server.features.infoMedia.category;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.features.infoMedia.base.InfoMediaEntity;
import app.milo.server.features.infoMedia.category.translations.InfoMediaCategoryTranslatableEntity;
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
@Table(name = "info_media_categories")
public class InfoMediaCategoryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String name;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private Set<InfoMediaEntity> infoMedia;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<InfoMediaCategoryTranslatableEntity> translatables;
}
