package app.wooportal.server.core.base;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

import app.wooportal.server.core.utils.ReflectionUtils;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@GraphQLType
public abstract class BaseEntity implements Serializable, Comparable<BaseEntity> {

  @Serial
  private static final long serialVersionUID = -8635539420496649618L;

  @Id
  @Column(columnDefinition = "CHAR")
  protected String id;  

  @LastModifiedDate
  @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
  protected OffsetDateTime modified;

  @CreatedDate
  @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", 
      updatable = false)
  protected OffsetDateTime created;
  
  @Override
  public int hashCode() {
    return getId() != null
        ? Objects.hash(getId())
        : super.hashCode();
  }
  
  @Override
  public boolean equals(Object other) {
    if (!getClass().equals(other.getClass())) {
      return false;
    }
    
    return getId() != null
        ? id.equals(((BaseEntity) other).id) || id == ((BaseEntity) other).id
        : super.equals(other);
  }
  
  @Override
  public int compareTo(@NotNull BaseEntity o) {
    return getId() != null
        ? getId().compareTo(o.getId())
        : 0;
  }
  
  public BaseEntity set(String fieldName, Object value) {
    ReflectionUtils.set(fieldName, this, value);
    return this;
  }

}
