package com.neighborhood.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class NotificationRepositoryTest {

  private NotificationRepository notificationRepository;

  @AfterEach
  void tearDown() {

    notificationRepository = null;
  }

  @Test
  void findByPlayerId() {
    // create a mock PlayerCareerHistoryRepository
    notificationRepository = mock(NotificationRepository.class);

    // call the method to be tested
    final var result = notificationRepository.findByPlayerId(1L);

    // check if the result is not null
    assertNotNull(result);
  }
}