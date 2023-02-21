package com.neighborhood.application.mappers;

import com.neighborhood.application.Court.CourtDto;
import com.neighborhood.domain.Court;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourtMapper {

  @Autowired
  private ModelMapper modelMapper;

  public CourtDto toDto(final Court entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, CourtDto.class);
  }

  public Court toEntity(final CourtDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, Court.class);
  }
}
