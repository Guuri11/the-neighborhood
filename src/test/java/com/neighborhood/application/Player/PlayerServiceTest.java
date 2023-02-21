package com.neighborhood.application.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.neighborhood.application.NotFoundException;
import com.neighborhood.application.mappers.PlayerMapper;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.infrastructure.persistence.PlayerRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

  @Test
  public void givenPlayerRepository_whenCallingAll_thenReturnPlayerDtos() {
    // Given
    when(playerRepository.findAll()).thenReturn(List.of(player));
    when(playerMapper.toDto(player)).thenReturn(playerDto);
    // When
    final Collection<PlayerDto> result = playerService.all();
    // Then
    assertFalse(result.isEmpty());
  }

  @Test
  public void givenPlayerRepository_whenCallingOneWithValidId_thenReturnPlayerDto() {
    // Given
    when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
    when(playerMapper.toDto(player)).thenReturn(playerDto);

    // When
    final PlayerDto result = playerService.one(player.getId());

    // Then
    assertEquals(result, playerDto);
  }

  @Test
  public void givenPlayerRepository_whenCallingOneWithInvalidId_thenThrowNotFoundException() {
    // given
    final Long invalidId = 100L;
    when(playerRepository.findById(invalidId)).thenReturn(Optional.empty());

    // when & then
    assertThrows(NotFoundException.class, () -> playerService.one(invalidId));
  }

  @Test
  public void givenPlayerRepository_whenCallingMeWithInvalidAuthentication_thenThrowNotFoundException() {
    // Given
    when(playerRepository.findById(1L)).thenReturn(Optional.empty());
    // When & Then
    assertThrows(NotFoundException.class, () -> playerService.one(1L));
  }

  @Test
  public void givenPlayerRepository_whenCallingDeleteWithValidId_thenCallRepositoryDeleteById() {
    // Given

    // When
    playerService.delete(player.getId());

    // Then
    verify(playerRepository).deleteById(player.getId());
  }

  @Test
  void update_ShouldReturnUpdatedPlayerDto_WhenValidInput() {
    // given
    when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
    when(playerDto.getEmail()).thenReturn("new-email@gmail.com");
    when(playerMapper.toDto(playerRepository.save(player))).thenReturn(playerDto);
    // when
    final PlayerDto actualPlayerDto = playerService.update(playerDto, 1L);

    // then
    assertEquals(actualPlayerDto.getEmail(), "new-email@gmail.com");
  }

  @Mock
  private PlayerRepository playerRepository;

  @Mock
  private PlayerMapper playerMapper;

  @Mock
  private Player player;

  @Mock
  private PlayerDto playerDto;

  @Mock
  private Authentication authentication;

  @InjectMocks
  private PlayerService playerService;
}
