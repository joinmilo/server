package app.wooportal.server.base.newsFeed;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.newsFeed.translations.NewsFeedTranslatableEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "news_feeds")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class NewsFeedEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String content;
  
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<NewsFeedTranslatableEntity> translatables;

}
