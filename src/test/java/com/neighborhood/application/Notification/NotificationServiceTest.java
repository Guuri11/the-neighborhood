package com.neighborhood.application.Notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.neighborhood.application.mappers.NotificationMapper;
import com.neighborhood.domain.Notification.Notification;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.infrastructure.persistence.NotificationRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

  final Long ID = 1L;
  @Mock
  Notification notification;
  @Mock
  NotificationDto notificationDto;
  @Mock
  NotificationRepository notificationRepository;
  @Mock
  NotificationMapper notificationMapper;
  @Mock
  Player player;
  @InjectMocks
  NotificationService notificationService;

  @Test
  void allByPlayer() {
    // Given
    Mockito.when(notificationRepository.findByPlayerId(ID))
        .thenReturn(List.of(notification));
    Mockito.when(notificationMapper.toDto(notification))
        .thenReturn(notificationDto);

    // When
    final Collection<NotificationDto> result = notificationService.allByPlayer(ID);

    // Then
    assertNotNull(result);
  }

  @Test
  void oneById() {
    // Given
    Mockito.when(notificationRepository.findById(ID))
        .thenReturn(Optional.of(notification));
    Mockito.when(notification.getPlayer())
        .thenReturn(player);
    Mockito.when(notificationMapper.toDto(notification))
        .thenReturn(notificationDto);

    // When
    final NotificationDto result = notificationService.oneById(ID, player.getId());

    // Then
    assertEquals(result, notificationDto);
  }

  @Test
  void create() {
    // Given
    Mockito.when(notificationRepository.save(notification))
        .thenReturn(notification);
    Mockito.when(notificationMapper.toDto(notification))
        .thenReturn(notificationDto);

    // When
    final NotificationDto result = notificationService.create(notification);

    // Then
    assertEquals(result, notificationDto);
  }

  @Test
  void update() {
    // Given
    Mockito.when(notificationRepository.findById(ID))
        .thenReturn(Optional.of(notification));
    Mockito.when(notification.getPlayer())
        .thenReturn(player);
    Mockito.when(notificationRepository.save(notification))
        .thenReturn(notification);
    Mockito.when(notificationMapper.toDto(notification))
        .thenReturn(notificationDto);
    // When
    final NotificationDto result = notificationService.update(notification, ID, player.getId());

    // Then
    assertEquals(result, notificationDto);
  }

  @Test
  void deleteTest() {
    // Given
    final Long id = 1L;
    final Notification notification = new Notification();
    player.setId(id);
    notification.setId(id);
    notification.setPlayer(player);
    Mockito.when(notificationRepository.findById(id))
        .thenReturn(Optional.of(notification));

    // When
    notificationService.delete(id, player.getId());

    // Then
    Mockito.verify(notificationRepository, Mockito.times(1))
        .delete(notification);
  }
}