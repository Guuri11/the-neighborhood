package com.neighborhood.application.PlayerCareerHistory;

import com.neighborhood.application.NotFoundException;
import com.neighborhood.application.mappers.PlayerCareerHistoryMapper;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.domain.PlayerCareerHistory;
import com.neighborhood.infrastructure.persistence.PlayerCareerHistoryRepository;
import com.neighborhood.infrastructure.persistence.PlayerRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerCareerHistoryService {

  private final PlayerCareerHistoryRepository repository;
  private final PlayerCareerHistoryMapper mapper;
  private final PlayerRepository playerRepository;

  PlayerCareerHistoryService(final PlayerCareerHistoryRepository repository,
      final PlayerCareerHistoryMapper mapper,
      final PlayerRepository playerRepository) {

    this.mapper = mapper;
    this.repository = repository;
    this.playerRepository = playerRepository;
  }

  public Collection<PlayerCareerHistoryDto> findByPlayer(@NotNull final Long id) {

    return repository.findByPlayerId(id)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  public PlayerCareerHistoryDto create(@NotNull @Valid final PlayerCareerHistory playerCareerHistory,
      @NotNull final Long playerId) {

    final Player player = playerRepository.findById(playerId)
        .orElseThrow(() -> new NotFoundException(playerId));

    playerCareerHistory.setPlayer(player);
    playerCareerHistory.setCreatedAt(LocalDateTime.now());
    playerCareerHistory.setUpdatedAt(LocalDateTime.now());

    final PlayerCareerHistory saved = repository.save(playerCareerHistory);

    return mapper.toDto(saved);
  }

  public PlayerCareerHistoryDto update(@NotNull @Valid final PlayerCareerHistory playerCareerHistory,
      @NotNull final Long id) {

    final PlayerCareerHistory existing = repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    existing.setLeague(playerCareerHistory.getLeague());
    existing.setUpdatedAt(LocalDateTime.now());

    final PlayerCareerHistory saved = repository.save(existing);

    return mapper.toDto(saved);
  }

  public void delete(@NotNull final Long id) {

    final PlayerCareerHistory existing = repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    repository.delete(existing);
  }
}
