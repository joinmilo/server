package app.milo.server.features.infoMedia.base;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.media.base.MediaEntity;
import app.milo.server.features.infoMedia.category.InfoMediaCategoryEntity;
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
@Table(name = "info_media")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class InfoMediaEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private InfoMediaCategoryEntity category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private MediaEntity media;

}
