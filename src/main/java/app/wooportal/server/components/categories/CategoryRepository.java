package app.wooportal.server.components.categories;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends DataRepository<CategoryEntity> {

}
