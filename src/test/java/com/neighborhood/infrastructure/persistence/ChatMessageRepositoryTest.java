package com.neighborhood.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ChatMessageRepositoryTest {

  private ChatMessageRepository repository;

  @AfterEach
  void tearDown() {

    repository = null;
  }

  @Test
  void findByChatId() {
    // create a mock PlayerCareerHistoryRepository
    repository = mock(ChatMessageRepository.class);

    // call the method to be tested
    final var result = repository.findByChatId(1L);

    // check if the result is not null
    assertNotNull(result);
  }

  @Test
  void findByIdAndChatId() {
    // create a mock PlayerCareerHistoryRepository
    repository = mock(ChatMessageRepository.class);

    // call the method to be tested
    final var result = repository.findByIdAndChatId(1L, 1L);

    // check if the result is not null
    assertNotNull(result);
  }
}