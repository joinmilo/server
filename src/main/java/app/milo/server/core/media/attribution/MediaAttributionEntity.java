package app.milo.server.core.media.attribution;

import java.io.Serial;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.milo.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Possibility to give credits to media author.
 * Some stock image providers require to give proper
 * TASL attribution (title, author, source, license)
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "media_attributions")
public class MediaAttributionEntity extends BaseEntity {
 
  @Serial
  private static final long serialVersionUID = 1L;
  
  private String title;

  private String author;
  
  private String source;
  
  private String license;
  
}
