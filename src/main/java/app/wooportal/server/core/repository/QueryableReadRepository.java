package app.wooportal.server.core.repository;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.querydsl.core.types.Predicate;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.PageableList;

@NoRepositoryBean
interface QueryableReadRepository<T extends BaseEntity> extends Repository<T, String> {
  
  PageableList<T> findAll(CollectionRepositoryQuery<T> query);
  
  Optional<T> findOne(BaseRepositoryQuery<T> query);
  
  boolean exists(Predicate predicate);
 
}
