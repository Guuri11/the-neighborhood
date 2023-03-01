package com.neighborhood.application.Notification;

import com.neighborhood.application.NotFoundException;
import com.neighborhood.application.mappers.NotificationMapper;
import com.neighborhood.domain.Notification.Notification;
import com.neighborhood.infrastructure.persistence.NotificationRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;
  private final NotificationMapper notificationMapper;

  public NotificationService(final NotificationRepository notificationRepository,
      final NotificationMapper notificationMapper) {

    this.notificationRepository = notificationRepository;
    this.notificationMapper = notificationMapper;
  }

  public Collection<NotificationDto> allByPlayer(final Long id) {

    return notificationRepository.findByPlayerId(id)
        .stream()
        .map(notificationMapper::toDto)
        .collect(Collectors.toList());
  }

  public NotificationDto oneById(final Long id, final Long playerId) {

    return notificationRepository.findById(id)
        .map(f -> {
          if (checkAuthorization(f, playerId)) {
            throw new NotFoundException(id);
          }
          return f;
        })
        .map(notificationMapper::toDto)
        .orElseThrow(() -> new NotFoundException(id));
  }

  public NotificationDto create(final Notification notification) {

    notification.setCreatedAt(LocalDateTime.now());
    notification.setUpdatedAt(LocalDateTime.now());
    return notificationMapper.toDto(notificationRepository.save(notification));
  }

  public NotificationDto update(final Notification notification, final Long id, final Long playerId) {

    final Notification existing = notificationRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    if (checkAuthorization(notification, playerId)) {
      throw new NotFoundException(id);
    }
    existing.setUpdatedAt(LocalDateTime.now());
    existing.setRead(notification.isRead());

    return notificationMapper.toDto(notificationRepository.save(existing));
  }

  public void delete(final Long id, final Long playerId) {

    final Notification notification = notificationRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    if (checkAuthorization(notification, playerId)) {
      throw new NotFoundException(id);
    }

    notificationRepository.delete(notification);
  }

  private boolean checkAuthorization(final Notification notification, final Long playerId) {

    return !Objects.equals(notification.getPlayer()
        .getId(), playerId);
  }
}
