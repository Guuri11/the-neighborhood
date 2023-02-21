package com.neighborhood.application.mappers;

import com.neighborhood.application.PlayerCareerHistory.PlayerCareerHistoryDto;
import com.neighborhood.domain.PlayerCareerHistory;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerCareerHistoryMapper {

  @Autowired
  private ModelMapper modelMapper;

  public PlayerCareerHistoryDto toDto(final PlayerCareerHistory entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, PlayerCareerHistoryDto.class);
  }

  public PlayerCareerHistory toEntity(final PlayerCareerHistoryDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, PlayerCareerHistory.class);
  }
}
