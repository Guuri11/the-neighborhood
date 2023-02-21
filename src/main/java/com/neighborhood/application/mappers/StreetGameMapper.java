package com.neighborhood.application.mappers;

import com.neighborhood.application.StreetGame.StreetGameDto;
import com.neighborhood.domain.StreetGame.StreetGame;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreetGameMapper {

  @Autowired
  private ModelMapper modelMapper;

  public StreetGameDto toDto(final StreetGame entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, StreetGameDto.class);
  }

  public StreetGame toEntity(final StreetGameDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, StreetGame.class);
  }
}
