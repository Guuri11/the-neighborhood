package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.Notification.NotificationDto;
import com.neighborhood.application.Notification.NotificationService;
import com.neighborhood.domain.Notification.Notification;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController extends BaseController {

  private final NotificationService notificationService;

  @GetMapping
  public Collection<NotificationDto> all(final Long id) {

    this.logger.info("Request: GET /notification by:{}", id.toString());
    return notificationService.allByPlayer(id);
  }

  @GetMapping("/{id}")
  public NotificationDto one(final Long id, final Authentication authentication) {

    this.logger.info("Request: GET /notification/{} by: {}", id.toString(), authentication.getName());
    return notificationService.oneById(id, Long.parseLong(authentication.getName()));
  }

  @PutMapping("/{id}")
  NotificationDto replace(@RequestBody final Notification notification, final Long id,
      final Authentication authentication) {

    this.logger.info("Request: PUT /notification/{} by: {} with body {}", id.toString(), authentication.getName(),
        notification.toString());
    return notificationService.update(notification, id, Long.parseLong(authentication.getName()));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> delete(final Authentication authentication, final Long id) {

    this.logger.info("Request: DELETE /notification/{} by {}", id.toString(), authentication.getName());
    notificationService.delete(id, Long.parseLong(authentication.getName()));
    return ResponseEntity.noContent()
        .build();
  }
}
