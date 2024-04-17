package app.milo.server.features.contest.participation.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ContestParticipationTranslatableRepository
    extends TranslationRepository<ContestParticipationTranslatableEntity> {

}
