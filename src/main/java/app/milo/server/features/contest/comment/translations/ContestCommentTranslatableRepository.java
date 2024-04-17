package app.milo.server.features.contest.comment.translations;


import org.springframework.stereotype.Repository;
import app.milo.server.core.i18n.translation.TranslationRepository;

@Repository
public interface ContestCommentTranslatableRepository
    extends TranslationRepository<ContestCommentTranslatableEntity> {

}
