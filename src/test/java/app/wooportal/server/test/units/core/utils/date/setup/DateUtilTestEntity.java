package app.wooportal.server.test.units.core.utils.date.setup;

import javax.persistence.Table;

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
@Table(name = "dateutil_test")
public class DateUtilTestEntity extends BaseEntity {
  private static final long serialVersionUID = 1L;
  
  private String field;
  
}
