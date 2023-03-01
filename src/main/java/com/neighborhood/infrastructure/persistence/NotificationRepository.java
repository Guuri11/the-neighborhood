package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.Notification.Notification;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

  Collection<Notification> findByPlayerId(Long playerId);
}