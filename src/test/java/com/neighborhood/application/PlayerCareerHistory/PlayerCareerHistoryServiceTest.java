package com.neighborhood.application.PlayerCareerHistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.neighborhood.application.mappers.PlayerCareerHistoryMapper;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.domain.PlayerCareerHistory;
import com.neighborhood.infrastructure.persistence.PlayerCareerHistoryRepository;
import com.neighborhood.infrastructure.persistence.PlayerRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PlayerCareerHistoryServiceTest {

  final Long ID = 1L;
  @Mock
  PlayerCareerHistory playerCareerHistory;
  @Mock
  PlayerCareerHistoryDto playerCareerHistoryDto;
  @Mock
  PlayerCareerHistoryRepository playerCareerHistoryRepository;
  @Mock
  PlayerCareerHistoryMapper playerCareerHistoryMapper;
  @Mock
  PlayerRepository playerRepository;
  @Mock
  Player player;
  @InjectMocks
  PlayerCareerHistoryService playerCareerHistoryService;

  @Test
  void findByPlayer() {
    // Given
    Mockito.when(playerCareerHistoryRepository.findByPlayerId(ID))
        .thenReturn(List.of(playerCareerHistory));
    Mockito.when(playerCareerHistoryMapper.toDto(playerCareerHistory))
        .thenReturn(playerCareerHistoryDto);
    // When
    final Collection<PlayerCareerHistoryDto> result = playerCareerHistoryService.findByPlayer(ID);
    // Then
    assertFalse(result.isEmpty());
  }

  @Test
  void create() {
    // Given
    Mockito.when(playerRepository.findById(ID))
        .thenReturn(Optional.of(player));
    Mockito.when(playerCareerHistoryRepository.save(playerCareerHistory))
        .thenReturn(playerCareerHistory);
    Mockito.when(playerCareerHistoryMapper.toDto(playerCareerHistory))
        .thenReturn(playerCareerHistoryDto);

    // When
    final PlayerCareerHistoryDto result = playerCareerHistoryService.create(playerCareerHistory, ID);

    // Then
    assertEquals(result, playerCareerHistoryDto);
  }

  @Test
  void update() {
    // Given
    Mockito.when(playerCareerHistoryRepository.findById(ID))
        .thenReturn(Optional.of(playerCareerHistory));
    Mockito.when(playerCareerHistory.getLeague())
        .thenReturn("NBA");
    Mockito.when(playerCareerHistory.getPlayer())
        .thenReturn(player);
    Mockito.when(playerCareerHistory.getPlayer()
            .getId())
        .thenReturn(ID);
    Mockito.when(playerCareerHistoryRepository.save(playerCareerHistory))
        .thenReturn(playerCareerHistory);
    Mockito.when(playerCareerHistoryMapper.toDto(playerCareerHistory))
        .thenReturn(playerCareerHistoryDto);

    // When
    final PlayerCareerHistoryDto result = playerCareerHistoryService.update(playerCareerHistory, ID, ID);

    // Then
    assertEquals(result, playerCareerHistoryDto);
  }


  @Test
  void deleteTest() {
    // Given
    final Long id = 1L;
    final Long playerId = 2L;
    final PlayerCareerHistory playerCareerHistory = new PlayerCareerHistory();
    final Player player = new Player();
    player.setId(playerId);
    playerCareerHistory.setId(id);
    playerCareerHistory.setPlayer(player);
    Mockito.when(playerCareerHistoryRepository.findById(id))
        .thenReturn(Optional.of(playerCareerHistory));

    // When
    playerCareerHistoryService.delete(id, playerId);

    // Then
    Mockito.verify(playerCareerHistoryRepository, Mockito.times(1))
        .delete(playerCareerHistory);
  }

}