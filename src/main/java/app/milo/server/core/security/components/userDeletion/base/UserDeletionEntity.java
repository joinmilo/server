package app.milo.server.core.security.components.userDeletion.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.milo.server.core.base.BaseEntity;
import app.milo.server.core.i18n.annotations.Translatable;
import app.milo.server.core.security.components.userDeletion.base.translations.UserDeletionTranslatableEntity;
import app.milo.server.core.security.components.userDeletion.type.UserDeletionTypeEntity;
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
@Table(name = "user_deletions")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class UserDeletionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String content;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<UserDeletionTranslatableEntity> translatables;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_deletion_user_deletion_types", joinColumns = @JoinColumn(name = "user_deletion_id"),
      inverseJoinColumns = @JoinColumn(name = "user_deletion_type_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_deletion_id", "user_deletion_type_id"})})
  
  private List<UserDeletionTypeEntity> types = new ArrayList<>();

}
