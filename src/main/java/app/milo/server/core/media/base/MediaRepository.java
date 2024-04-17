package app.milo.server.core.media.base;

import org.springframework.stereotype.Repository;
import app.milo.server.core.repository.DataRepository;

@Repository
interface MediaRepository extends DataRepository<MediaEntity> {

}
