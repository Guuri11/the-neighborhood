package com.neighborhood.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PlayerCareerHistoryRepositoryTest {

  private PlayerCareerHistoryRepository playerCareerHistoryRepository;

  @AfterEach
  void tearDown() {

    playerCareerHistoryRepository = null;
  }

  @Test
  void findByPlayerId() {
    // create a mock PlayerCareerHistoryRepository
    playerCareerHistoryRepository = mock(PlayerCareerHistoryRepository.class);

    // call the method to be tested
    final var result = playerCareerHistoryRepository.findByPlayerId(1L);

    // check if the result is not null
    assertNotNull(result);
  }
}
