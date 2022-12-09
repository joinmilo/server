package app.wooportal.server.components.categories;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.components.events.base.EventEntity;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "categories")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class CategoryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(unique = true, nullable = false)
  private String color;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private Set<EventEntity> events;

  @Column(unique = true, nullable = false)
  private String icon;
}
