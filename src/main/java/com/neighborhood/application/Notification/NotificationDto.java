package com.neighborhood.application.Notification;

import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.domain.Notification.MessageType;
import com.neighborhood.domain.Notification.Notification;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link Notification} entity
 */
@Data
public class NotificationDto implements Serializable {

  private final Long id;
  private final MessageType message;
  private final PlayerDto player;
  private final boolean read;
}