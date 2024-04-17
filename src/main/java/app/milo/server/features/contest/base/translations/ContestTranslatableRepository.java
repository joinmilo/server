package app.milo.server.features.contest.base.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ContestTranslatableRepository
    extends TranslationRepository<ContestTranslatableEntity> {

}
