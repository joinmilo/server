package app.wooportal.server.base.cms.page.widgetAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.base.cms.page.attributeType.PageAttributeTypeEntity;
import app.wooportal.server.base.cms.page.widget.PageWidgetEntity;
import app.wooportal.server.base.cms.page.widgetAttribute.translations.PageWidgetAttributeTranslatableEntity;
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
@Table(name = "page_widget_attributes")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class PageWidgetAttributeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Translatable
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  protected PageAttributeTypeEntity type;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PageWidgetAttributeTranslatableEntity> translatables;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "page_widget_attributes_media",
    joinColumns = @JoinColumn(name = "widget_type_id"),
    inverseJoinColumns = @JoinColumn(name = "attribute_type_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"widget_type_id", "attribute_type_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<PageAttributeTypeEntity> media = new ArrayList<>();
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "page_widget_widget_attributes",
    joinColumns = @JoinColumn(name = "widget_attribute_id"),
    inverseJoinColumns = @JoinColumn(name = "widget_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"widget_attribute_id", "widget_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<PageWidgetEntity> widgets = new ArrayList<>();

}
