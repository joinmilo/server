package app.milo.server.base.address.base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import app.milo.server.base.address.suburb.SuburbEntity;
import app.milo.server.core.base.BaseEntity;
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
@Table(name = "addresses")
public class AddressEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String houseNumber;

  @Column(nullable = false)
  private Float latitude;

  @Column(nullable = false)
  private Float longitude;

  @Column(nullable = false)
  private String place;

  @Column(nullable = false)
  private String postalCode;

  @Column(nullable = false)
  private String street;

  @ManyToOne(fetch = FetchType.LAZY)
  private SuburbEntity suburb;
}
