package app.wooportal.server.base.userDeletion.base;

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

import app.wooportal.server.base.userDeletion.base.translations.UserDeletionTranslatableEntity;
import app.wooportal.server.base.userDeletion.type.UserDeletionTypeEntity;
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
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<UserDeletionTypeEntity> types = new ArrayList<>();

}
