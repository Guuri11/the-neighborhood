package com.neighborhood.application.mappers;

import com.neighborhood.application.ChatMessage.ChatMessageDto;
import com.neighborhood.domain.ChatMessage;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageMapper {

  @Autowired
  private ModelMapper modelMapper;

  public ChatMessageDto toDto(final ChatMessage entity) {

    return Objects.isNull(entity) ? null : modelMapper.map(entity, ChatMessageDto.class);
  }

  public ChatMessage toEntity(final ChatMessageDto dto) {

    return Objects.isNull(dto) ? null : modelMapper.map(dto, ChatMessage.class);
  }
}
