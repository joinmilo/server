package app.wooportal.server.base.address.base;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AddressPredicateBuilder extends PredicateBuilder<QAddressEntity, AddressEntity> {

  public AddressPredicateBuilder() {
    super(QAddressEntity.addressEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.houseNumber.likeIgnoreCase(term).or(query.place.likeIgnoreCase(term))
        .or(query.street.likeIgnoreCase(term)).or(query.postalCode.likeIgnoreCase(term));
  }

  public BooleanExpression withStreet(String street) {
    return street != null
        ? query.street.equalsIgnoreCase(street)
        : null;
  }

  public BooleanExpression withHouseNumber(String houseNumber) {
    return houseNumber != null
        ? query.houseNumber.equalsIgnoreCase(houseNumber)
        : null;
  }
  
  public BooleanExpression withPlace(String place) {
    return place != null
        ? query.place.equalsIgnoreCase(place)
        : null;
  }
  
  public BooleanExpression withPostalCode(String postalCode) {
    return postalCode != null
        ? query.postalCode.equalsIgnoreCase(postalCode)
        : null;
  }
}
