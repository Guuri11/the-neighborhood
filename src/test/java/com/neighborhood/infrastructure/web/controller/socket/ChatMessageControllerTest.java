package com.neighborhood.infrastructure.web.controller.socket;

import com.neighborhood.application.Chat.ChatDto;
import com.neighborhood.application.Chat.ChatService;
import com.neighborhood.application.ChatMessage.ChatMessageDto;
import com.neighborhood.application.ChatMessage.ChatMessageService;
import com.neighborhood.application.mappers.ChatMapper;
import com.neighborhood.application.mappers.ChatMessageMapper;
import com.neighborhood.domain.Chat;
import com.neighborhood.domain.ChatMessage;
import com.neighborhood.domain.Player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChatMessageControllerTest {

  @Mock
  private ChatService chatService;

  @Mock
  private ChatMessageService chatMessageService;

  @Mock
  private ChatMapper chatMapper;

  @Mock
  private ChatMessageMapper chatMessageMapper;

  @Mock
  private Player player;

  @Mock
  private Chat chat;

  final Long ID = 1L;

  @Mock
  private ChatDto chatDto;

  @Mock
  private ChatMessage chatMessage;

  @Mock
  private ChatMessageDto chatMessageDto;

  @InjectMocks
  private ChatMessageSocketController chatMessageController;

  @Test
  void shouldReturnChatMessageDtoWhenMessageSent() {
    // Given
    Mockito.when(chatMapper.toEntity(chatDto))
        .thenReturn(chat);
    Mockito.when(chatMessage.getChat())
        .thenReturn(chat);
    Mockito.when(chatService.oneById(chat.getId(), player.getId()))
        .thenReturn(chatDto);
    Mockito.when(chatMessageMapper.toDto(chatMessage))
        .thenReturn(chatMessageDto);

    // When
    chatMessageController.sendMessage(player, chatMessage);

    // Then
    Mockito.verify(chatMessageService)
        .create(chatMessage);
  }
}
