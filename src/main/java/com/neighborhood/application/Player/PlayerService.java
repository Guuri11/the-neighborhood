package com.neighborhood.application.Player;

import com.neighborhood.application.NotFoundException;
import com.neighborhood.application.mappers.PlayerMapper;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.infrastructure.persistence.PlayerRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private final PlayerRepository repository;
  private final PlayerMapper mapper;

  PlayerService(final PlayerRepository repository, final PlayerMapper mapper) {

    this.repository = repository;
    this.mapper = mapper;
  }

  public Collection<PlayerDto> all() {

    return repository.findAll()
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  public PlayerDto one(final Long id) {

    return repository.findById(id)
        .map(mapper::toDto)
        .orElseThrow(() -> new NotFoundException(id));
  }

  public PlayerDto update(final PlayerDto playerDto, final Long id) {

    final Player player = repository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    // Update the player entity
    player.setName(playerDto.getName());
    player.setEmail(playerDto.getEmail());
    player.setNickname(playerDto.getNickname());
    player.setHeight(playerDto.getHeight());
    player.setWeight(playerDto.getWeight());
    player.setBirthdate(playerDto.getBirthdate());
    player.setLevel(playerDto.getLevel());
    player.setExperience(playerDto.getExperience());
    player.setPosition(playerDto.getPosition());
    player.setArchetype(playerDto.getArchetype());
    player.setUpdatedAt(LocalDateTime.now());

    // Save the updated player entity
    return mapper.toDto(repository.save(player));
  }

  public void delete(final Long id) {

    repository.deleteById(id);
  }
}
