package app.wooportal.server.features.navigator.connections;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.navigator.base.NavigatorChoiceEntity;
import app.wooportal.server.features.navigator.page.NavigatorPageEntity;
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
@Table(name = "navigator_connections")
public class NavigatorConnectionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private NavigatorChoiceEntity choice;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private NavigatorPageEntity page;
  
  private Integer order;
}
