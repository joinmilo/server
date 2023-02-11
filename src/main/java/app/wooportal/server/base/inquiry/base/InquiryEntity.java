package app.wooportal.server.base.inquiry.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.inquiry.category.InquiryCategoryEntity;
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
@Table(name = "inquiries")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class InquiryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private InquiryCategoryEntity category;

}
