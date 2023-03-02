package app.wooportal.server.base.contact;

import javax.persistence.Entity;
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
@Entity
@Table(name = "contacts")
public class ContactEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String email;

  private String name;
  
  private String phone;

  private Boolean preferredContact;

}
