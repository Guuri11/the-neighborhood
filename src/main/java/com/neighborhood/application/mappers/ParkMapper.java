package com.neighborhood.application.mappers;

import com.neighborhood.application.Park.ParkDto;
import com.neighborhood.domain.Park;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkMapper {

  @Autowired
  private ModelMapper modelMapper;

  public ParkDto toDto(final Park entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, ParkDto.class);
  }

  public Park toEntity(final ParkDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, Park.class);
  }
}
