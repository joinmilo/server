package app.milo.server.core.i18n.translation;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.core.repository.DataRepository;


@NoRepositoryBean
public interface TranslationRepository<T extends TranslatableEntity<?>> extends DataRepository<T> {

  <E extends BaseEntity> T findByLanguageIdAndParentId(String language, String parent);

  <E extends BaseEntity> List<T> findByParentId(String parent);
}
