package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.PlayerStreetsStats.PlayerStreetStatsService;
import com.neighborhood.domain.PlayerStreetStats;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class PlayerStreetStatsControllerTest {

  final Long ID = 1L;
  @Mock
  PlayerStreetStatsService playerStreetStatsService;
  @Mock
  PlayerStreetStats playerStreetStats;
  @Mock
  Authentication authentication;
  @InjectMocks
  PlayerStreetStatsController playerStreetStatsController;

  @Test
  void all_shouldCallPlayerService() {
    // Given

    // When
    playerStreetStatsController.all(ID);

    // Then
    Mockito.verify(playerStreetStatsService)
        .findByPlayer(ID);
  }

  @Test
  void create_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");
    // When
    playerStreetStatsController.create(playerStreetStats, authentication);

    // Then
    Mockito.verify(playerStreetStatsService)
        .create(playerStreetStats, ID);

  }

  @Test
  void replace_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    playerStreetStatsController.replace(playerStreetStats, ID, authentication);

    // Then
    Mockito.verify(playerStreetStatsService)
        .update(playerStreetStats, ID, ID);

  }

  @Test
  void delete_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    playerStreetStatsController.delete(authentication, ID);

    // Then
    Mockito.verify(playerStreetStatsService)
        .delete(ID, ID);

  }
}