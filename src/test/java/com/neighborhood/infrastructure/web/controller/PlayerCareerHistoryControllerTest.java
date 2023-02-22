package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.PlayerCareerHistory.PlayerCareerHistoryService;
import com.neighborhood.domain.PlayerCareerHistory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class PlayerCareerHistoryControllerTest {

  final Long ID = 1L;
  @Mock
  PlayerCareerHistoryService playerCareerHistoryService;
  @Mock
  PlayerCareerHistory playerCareerHistory;
  @Mock
  Authentication authentication;
  @InjectMocks
  PlayerCareerHistoryController playerCareerHistoryController;

  @Test
  void all_shouldCallPlayerService() {
    // Given

    // When
    playerCareerHistoryController.all(ID);

    // Then
    Mockito.verify(playerCareerHistoryService)
        .findByPlayer(ID);
  }

  @Test
  void create_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");
    // When
    playerCareerHistoryController.create(playerCareerHistory, authentication);

    // Then
    Mockito.verify(playerCareerHistoryService)
        .create(playerCareerHistory, ID);

  }

  @Test
  void replace_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    playerCareerHistoryController.replace(playerCareerHistory, ID, authentication);

    // Then
    Mockito.verify(playerCareerHistoryService)
        .update(playerCareerHistory, ID, ID);

  }

  @Test
  void delete_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    playerCareerHistoryController.delete(authentication, ID);

    // Then
    Mockito.verify(playerCareerHistoryService)
        .delete(ID, ID);

  }
}