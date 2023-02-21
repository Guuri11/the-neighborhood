package com.neighborhood.application.mappers;

import com.neighborhood.application.Friendship.FriendshipDto;
import com.neighborhood.domain.Friendship;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendshipMapper {

  @Autowired
  private ModelMapper modelMapper;

  public FriendshipDto toDto(final Friendship entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, FriendshipDto.class);
  }

  public Friendship toEntity(final FriendshipDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, Friendship.class);
  }
}
