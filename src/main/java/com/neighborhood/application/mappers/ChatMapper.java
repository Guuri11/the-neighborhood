package com.neighborhood.application.mappers;

import com.neighborhood.application.Chat.ChatDto;
import com.neighborhood.domain.Chat;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper {

  @Autowired
  private ModelMapper modelMapper;

  public ChatDto toDto(final Chat entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, ChatDto.class);
  }

  public Chat toEntity(final ChatDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, Chat.class);
  }
}
