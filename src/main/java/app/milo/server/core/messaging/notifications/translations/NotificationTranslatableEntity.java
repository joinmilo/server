package app.milo.server.core.messaging.notifications.translations;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import app.milo.server.core.i18n.components.language.LanguageEntity;
import app.milo.server.core.i18n.entities.TranslatableEntity;
import app.milo.server.core.messaging.notifications.base.NotificationEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "notification_translatables")
public class NotificationTranslatableEntity extends TranslatableEntity<NotificationEntity> {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private NotificationEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private LanguageEntity language;
}
