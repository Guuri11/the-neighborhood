package com.neighborhood.application.mappers;

import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.domain.Player.Player;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

  @Autowired
  private ModelMapper modelMapper;

  public PlayerDto toDto(final Player entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, PlayerDto.class);
  }

  public Player toEntity(final PlayerDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, Player.class);
  }
}
