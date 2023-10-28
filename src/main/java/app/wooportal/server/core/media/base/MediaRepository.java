package app.wooportal.server.core.media.base;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.repository.DataRepository;

@Repository
interface MediaRepository extends DataRepository<MediaEntity> {

}
