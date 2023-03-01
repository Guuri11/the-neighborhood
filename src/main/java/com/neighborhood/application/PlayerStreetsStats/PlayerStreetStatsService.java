package com.neighborhood.application.PlayerStreetsStats;

import com.neighborhood.application.NotFoundException;
import com.neighborhood.application.mappers.PlayerStreetStatsMapper;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.domain.PlayerStreetStats;
import com.neighborhood.infrastructure.persistence.PlayerRepository;
import com.neighborhood.infrastructure.persistence.PlayerStreetStatsRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerStreetStatsService {

  private final PlayerStreetStatsRepository repository;
  private final PlayerStreetStatsMapper mapper;
  private final PlayerRepository playerRepository;

  PlayerStreetStatsService(final PlayerStreetStatsRepository repository,
      final PlayerStreetStatsMapper mapper,
      final PlayerRepository playerRepository) {

    this.mapper = mapper;
    this.repository = repository;
    this.playerRepository = playerRepository;
  }

  public Collection<PlayerStreetStatsDto> findByPlayer(@NotNull final Long id) {

    return repository.findByPlayerId(id)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  public PlayerStreetStatsDto create(@NotNull @Valid final PlayerStreetStats playerStreetStats,
      @NotNull final Long playerId) {

    final Player player = playerRepository.findById(playerId)
        .orElseThrow(() -> new NotFoundException(playerId));

    playerStreetStats.setPlayer(player);
    playerStreetStats.setCreatedAt(LocalDateTime.now());
    playerStreetStats.setUpdatedAt(LocalDateTime.now());

    final PlayerStreetStats saved = repository.save(playerStreetStats);

    return mapper.toDto(saved);
  }

  public PlayerStreetStatsDto update(@NotNull @Valid final PlayerStreetStats playerStreetStats,
      @NotNull final Long playerId, @NotNull final Long id) {

    final PlayerStreetStats existing = repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    if (!Objects.equals(existing.getPlayer()
        .getId(), playerId)) {
      throw new NotFoundException(id);
    }
    existing.setAssists(playerStreetStats.getAssists());
    existing.setBlock(playerStreetStats.getBlock());
    existing.setPoints(playerStreetStats.getPoints());
    existing.setRebounds(playerStreetStats.getRebounds());
    existing.setWon(playerStreetStats.getWon());
    existing.setSteals(playerStreetStats.getSteals());
    // TODO: handle this with a new function where the other player have to verify the stts
    existing.setVerified(playerStreetStats.getVerified());
    existing.setUpdatedAt(LocalDateTime.now());

    final PlayerStreetStats saved = repository.save(existing);

    return mapper.toDto(saved);
  }

  public void delete(@NotNull final Long id, @NotNull final Long playerId) {

    final PlayerStreetStats existing = repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    if (!Objects.equals(existing.getPlayer()
        .getId(), playerId)) {
      throw new NotFoundException(id);
    }

    repository.delete(existing);
  }
}
