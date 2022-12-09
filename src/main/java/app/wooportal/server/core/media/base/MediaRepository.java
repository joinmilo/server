package app.wooportal.server.core.media.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MediaRepository extends DataRepository<MediaEntity> {

}
