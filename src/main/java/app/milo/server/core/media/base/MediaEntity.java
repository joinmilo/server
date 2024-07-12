package app.milo.server.core.media.base;

import java.io.Serial;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.context.ApplicationContextAdapter;
import app.milo.server.core.media.attribution.MediaAttributionEntity;
import app.milo.server.core.media.storage.StorageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MediaEntity should not be used as direct reference from other entities
 * but rather be used within a proxy entity
 *
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "media")
public class MediaEntity extends BaseEntity {
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaAttributionEntity attribution;
  
  @Transient
  private String base64;

  @Serial
  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String extension;

  @Column(name = "mime_type", nullable = false)
  private String mimeType;

  private String name;
  
  private Long size;

  private String url;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity thumbnail;
  
  public String getUrl() {
    if (url == null || url.isBlank()) {
      var storageService = ApplicationContextAdapter.bean(StorageService.class);
      return storageService.getReadLocation(id);
    }
    return url;
  }
  
}
