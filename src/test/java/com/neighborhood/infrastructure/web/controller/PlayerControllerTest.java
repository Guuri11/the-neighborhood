package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.Player.PlayerService;
import com.neighborhood.application.mappers.PlayerMapper;
import com.neighborhood.domain.Player.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;

class PlayerControllerTest {

  @Test
  void all_shouldCallPlayerService() {
    // Given
    final PlayerService playerService = Mockito.mock(PlayerService.class);
    final PlayerController playerController = new PlayerController(playerService);

    // When
    playerController.all();

    // Then
    Mockito.verify(playerService)
        .all();
  }

  @Test
  void one_shouldCallPlayerServiceWithCorrectId() {
    // Given
    final Long playerId = 1L;
    final PlayerService playerService = Mockito.mock(PlayerService.class);
    final PlayerController playerController = new PlayerController(playerService);

    // When
    playerController.one(playerId);

    // Then
    Mockito.verify(playerService)
        .one(playerId);
  }

  @Test
  void replacePlayer_shouldCallPlayerServiceWithCorrectArguments() {
    // Given
    final Player newPlayer = new Player();
    final Authentication authentication = Mockito.mock(Authentication.class);
    final PlayerMapper mapper = Mockito.mock(PlayerMapper.class);
    final Long playerId = 1L;
    Mockito.when(authentication.getName())
        .thenReturn(playerId.toString());

    final PlayerService playerService = Mockito.mock(PlayerService.class);
    final PlayerController playerController = new PlayerController(playerService);

    // When
    playerController.replacePlayer(mapper.toDto(newPlayer), authentication);

    // Then
    Mockito.verify(playerService)
        .update(mapper.toDto(newPlayer), playerId);
  }

  @Test
  void deletePlayer_shouldCallPlayerServiceWithCorrectId() {
    // Given
    final Authentication authentication = Mockito.mock(Authentication.class);
    final Long playerId = 1L;
    Mockito.when(authentication.getName())
        .thenReturn(playerId.toString());

    final PlayerService playerService = Mockito.mock(PlayerService.class);
    final PlayerController playerController = new PlayerController(playerService);

    // When
    playerController.deletePlayer(authentication);

    // Then
    Mockito.verify(playerService)
        .delete(playerId);
  }
}