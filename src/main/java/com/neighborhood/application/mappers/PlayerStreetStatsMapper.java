package com.neighborhood.application.mappers;

import com.neighborhood.application.PlayerStreetsStats.PlayerStreetStatsDto;
import com.neighborhood.domain.PlayerStreetStats;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerStreetStatsMapper {

  @Autowired
  private ModelMapper modelMapper;

  public PlayerStreetStatsDto toDto(final PlayerStreetStats entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, PlayerStreetStatsDto.class);
  }

  public PlayerStreetStats toEntity(final PlayerStreetStatsDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, PlayerStreetStats.class);
  }
}
