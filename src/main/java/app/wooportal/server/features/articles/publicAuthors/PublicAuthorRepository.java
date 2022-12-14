package app.wooportal.server.features.articles.publicAuthors;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicAuthorRepository extends DataRepository<PublicAuthorEntity> {

}
