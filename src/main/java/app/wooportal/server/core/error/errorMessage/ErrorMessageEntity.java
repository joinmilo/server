package app.wooportal.server.core.error.errorMessage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.components.language.LanguageEntity;
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
@Table(name = "error_messages")
public class ErrorMessageEntity extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String code;
  
  @JoinColumn(nullable = false)
  private LanguageEntity language;
  
  @Column(nullable = false, unique = true)
  private String message;
}
