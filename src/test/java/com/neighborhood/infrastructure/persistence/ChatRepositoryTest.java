package com.neighborhood.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ChatRepositoryTest {

  private ChatRepository chatRepository;

  @AfterEach
  void tearDown() {

    chatRepository = null;
  }

  @Test
  void findByPlayerId() {
    // create a mock PlayerCareerHistoryRepository
    chatRepository = mock(ChatRepository.class);

    // call the method to be tested
    final var result = chatRepository.findByPlayerId(1L);

    // check if the result is not null
    assertNotNull(result);
  }
}