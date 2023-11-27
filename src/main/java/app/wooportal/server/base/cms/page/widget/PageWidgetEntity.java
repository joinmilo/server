package app.wooportal.server.base.cms.page.widget;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;
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
@Table(name = "page_widgets")
public class PageWidgetEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  protected PageWidgetTypeEntity type;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "page_widget_widget_attributes",
    joinColumns = @JoinColumn(name = "widget_id"),
    inverseJoinColumns = @JoinColumn(name = "widget_attribute_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"widget_id", "widget_attribute_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<PageWidgetAttributeEntity> attributes = new ArrayList<>();

}
