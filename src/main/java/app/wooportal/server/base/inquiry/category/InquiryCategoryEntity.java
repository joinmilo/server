package app.wooportal.server.base.inquiry.category;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.inquiry.base.InquiryEntity;
import app.wooportal.server.base.inquiry.translations.InquiryCategoryTranslatableEntity;
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
@Table(name = "inquiry_categories")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class InquiryCategoryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private Set<InquiryEntity> inquiries;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<InquiryCategoryTranslatableEntity> translatables;


}
