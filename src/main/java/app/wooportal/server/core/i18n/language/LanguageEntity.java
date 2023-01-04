package app.wooportal.server.core.i18n.language;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.cms.translations.FeatureTranslatableEntity;
import app.wooportal.server.base.cms.translations.LandingTranslatableEntity;
import app.wooportal.server.base.cms.translations.MenuTranslatableEntity;
import app.wooportal.server.base.cms.translations.PageTranslatableEntity;
import app.wooportal.server.base.userContexts.translations.UserContextTranslatableEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.messaging.translations.MessageTemplateTranslatableEntity;
import app.wooportal.server.core.security.components.role.translation.RoleTranslatableEntity;
import app.wooportal.server.features.articles.translations.ArticleCategoryTranslatableEntity;
import app.wooportal.server.features.articles.translations.ArticleCommentTranslatableEntity;
import app.wooportal.server.features.contests.translations.ContestParticipationTranslatableEntity;
import app.wooportal.server.features.contests.translations.ContestTranslatableEntity;
import app.wooportal.server.features.deals.translations.DealCategoryTranslatableEntity;
import app.wooportal.server.features.deals.translations.DealTranslatableEntity;
import app.wooportal.server.features.events.translations.EventCategoryTranslatableEntity;
import app.wooportal.server.features.events.translations.EventCommentTranslatableEntity;
import app.wooportal.server.features.events.translations.EventTargetGroupTranslatableEntity;
import app.wooportal.server.features.events.translations.EventTranslatableEntity;
import app.wooportal.server.features.forms.translations.FormTemplateTranslatableEntity;
import app.wooportal.server.features.forms.translations.FormTemplateTypeTranslatableEntity;
import app.wooportal.server.features.forms.translations.UserFormTemplateTranslatableEntity;
import app.wooportal.server.features.organisations.translations.OrganisationTranslatableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false, unique = true)
  private String locale;

  @Column(nullable = false, unique = true)
  private String name;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<EventCategoryTranslatableEntity> eventCategorys;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<EventCommentTranslatableEntity> eventComments;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<EventTranslatableEntity> events;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<EventTargetGroupTranslatableEntity> eventTargetGroups;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<UserContextTranslatableEntity> userContexts;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<OrganisationTranslatableEntity> organisations;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<FormTemplateTypeTranslatableEntity> formTemplateTypes;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<FormTemplateTranslatableEntity> formTemplates;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<UserFormTemplateTranslatableEntity> UserFormTemplate;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<DealCategoryTranslatableEntity> dealCategories;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<DealTranslatableEntity> deals;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<ArticleCommentTranslatableEntity> articleComments;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<ArticleCategoryTranslatableEntity> articleCategories;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<ContestTranslatableEntity> contests;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<ContestParticipationTranslatableEntity> contestParticipations;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<MenuTranslatableEntity> menues;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<PageTranslatableEntity> pages;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<LandingTranslatableEntity> landings;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<FeatureTranslatableEntity> features;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<MessageTemplateTranslatableEntity> messageTemplates;

  @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
  private Set<RoleTranslatableEntity> roles;

}
