package app.wooportal.server.base.cms.page.attributeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.base.cms.page.attributeType.translations.PageAttributeTypeTranslatableEntity;
import app.wooportal.server.base.cms.page.widgetAttribute.PageWidgetAttributeEntity;
import app.wooportal.server.base.cms.page.widgetType.PageWidgetTypeEntity;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "page_attribute_types")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class PageAttributeTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String code;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PageAttributeTypeTranslatableEntity> translatables;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
  private Set<PageWidgetAttributeEntity> widgetAttributes;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "page_widget_types_attribute_types",
    joinColumns = @JoinColumn(name = "attribute_type_id"),
    inverseJoinColumns = @JoinColumn(name = "widget_type_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"attribute_type_id", "widget_type_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<PageWidgetTypeEntity> widgetTypes = new ArrayList<>();

}
