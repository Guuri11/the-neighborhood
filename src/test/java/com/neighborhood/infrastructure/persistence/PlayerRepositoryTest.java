package com.neighborhood.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PlayerRepositoryTest {

  private PlayerRepository playerRepository;

  @AfterEach
  void tearDown() {

    playerRepository = null;
  }

  @Test
  void findByEmail() {
    // create a mock PlayerRepository
    playerRepository = mock(PlayerRepository.class);

    // call the method to be tested
    final var result = playerRepository.findByEmail("test@example.com");

    // check if the result is not null
    assertNotNull(result);
  }
}
