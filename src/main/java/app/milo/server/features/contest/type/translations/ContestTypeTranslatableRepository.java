package app.milo.server.features.contest.type.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ContestTypeTranslatableRepository
    extends TranslationRepository<ContestTypeTranslatableEntity> {

}
