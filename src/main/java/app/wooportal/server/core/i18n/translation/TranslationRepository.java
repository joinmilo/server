package app.wooportal.server.core.i18n.translation;

import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import app.wooportal.server.core.i18n.language.LanguageEntity;
import app.wooportal.server.core.repository.DataRepository;


@NoRepositoryBean
public interface TranslationRepository<T extends TranslatableEntity<?>> extends DataRepository<T> {

  <E extends BaseEntity> T findByLanguageAndParent(LanguageEntity language, E parent);

  <E extends BaseEntity> List<T> findByParent(E parent);
}
