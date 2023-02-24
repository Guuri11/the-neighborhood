package com.neighborhood.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class FriendshipRepositoryTest {

  private FriendshipRepository friendshipRepository;

  @AfterEach
  void tearDown() {

    friendshipRepository = null;
  }

  @Test
  void findByPlayerId() {
    // create a mock PlayerCareerHistoryRepository
    friendshipRepository = mock(FriendshipRepository.class);

    // call the method to be tested
    final var result = friendshipRepository.findByPlayerId(1L);

    // check if the result is not null
    assertNotNull(result);
  }
}