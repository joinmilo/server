package app.wooportal.server.core.media.base;

import java.io.Serial;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
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

  @Serial
  private static final long serialVersionUID = 1L;
  
  private String credits;

  @Column(name = "mime_type", nullable = false)
  private String mimeType;

  @Column(nullable = false)
  private String name;
  
}
