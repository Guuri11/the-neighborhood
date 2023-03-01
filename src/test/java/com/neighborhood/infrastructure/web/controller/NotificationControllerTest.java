package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.domain.Notification.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

  final Long ID = 1L;
  @Mock
  com.neighborhood.application.Notification.NotificationService NotificationService;
  @Mock
  Notification notification;
  @Mock
  Authentication authentication;
  @InjectMocks
  NotificationController notificationController;

  @Test
  void all_shouldCallPlayerService() {
    // Given

    // When
    notificationController.all(ID);

    // Then
    Mockito.verify(NotificationService)
        .allByPlayer(ID);
  }

  @Test
  void replace_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    notificationController.replace(notification, ID, authentication);

    // Then
    Mockito.verify(NotificationService)
        .update(notification, ID, ID);

  }

  @Test
  void delete_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    notificationController.delete(authentication, ID);

    // Then
    Mockito.verify(NotificationService)
        .delete(ID, ID);

  }
}