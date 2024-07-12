package app.milo.server.features.deal.components.base.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.features.deal.components.base.DealEntity;
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
@Table(name = "deal_translatables")
public class DealTranslatableEntity extends TranslatableEntity<DealEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String content;

  private String shortDescription;

}
