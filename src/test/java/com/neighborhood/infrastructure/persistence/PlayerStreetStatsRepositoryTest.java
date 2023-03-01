package com.neighborhood.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PlayerStreetStatsRepositoryTest {

  private PlayerStreetStatsRepository repository;

  @AfterEach
  void tearDown() {

    repository = null;
  }

  @Test
  void findByPlayerId() {
    // create a mock PlayerCareerHistoryRepository
    repository = mock(PlayerStreetStatsRepository.class);

    // call the method to be tested
    final var result = repository.findByPlayerId(1L);

    // check if the result is not null
    assertNotNull(result);
  }
}
