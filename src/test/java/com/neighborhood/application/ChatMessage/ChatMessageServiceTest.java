package com.neighborhood.application.ChatMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.neighborhood.application.mappers.ChatMessageMapper;
import com.neighborhood.domain.Chat;
import com.neighborhood.domain.ChatMessage;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.infrastructure.persistence.ChatMessageRepository;
import com.neighborhood.infrastructure.persistence.ChatRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChatMessageServiceTest {

  final Long ID = 1L;
  @Mock
  ChatMessage chatMessage;
  @Mock
  ChatMessageDto chatMessageDto;
  @Mock
  ChatMessageRepository chatMessageRepository;
  @Mock
  ChatRepository chatRepository;
  @Mock
  ChatMessageMapper chatMessageMapper;
  @Mock
  Chat chat;
  @Mock
  Player player;
  @InjectMocks
  ChatMessageService chatMessageService;

  @Test
  void findAllByChatIdAndPlayerId() {
    // Given
    Mockito.when(chatRepository.findByIdAndPlayersId(ID, ID))
        .thenReturn(chat);
    Mockito.when(chatMessageRepository.findByChatId(ID))
        .thenReturn(List.of(chatMessage));
    Mockito.when(chatMessageMapper.toDto(chatMessage))
        .thenReturn(chatMessageDto);

    // When
    final Collection<ChatMessageDto> result = chatMessageService.findAllByChatIdAndPlayerId(ID, ID);

    // Then
    assertNotNull(result);
  }

  @Test
  void create() {
    // Given
    Mockito.when(chatMessageRepository.save(chatMessage))
        .thenReturn(chatMessage);
    Mockito.when(chatMessageMapper.toDto(chatMessage))
        .thenReturn(chatMessageDto);

    // When
    final ChatMessageDto result = chatMessageService.create(chatMessage);

    // Then
    assertEquals(result, chatMessageDto);
  }

  @Test
  void deleteTest() {
    // Given
    Mockito.when(chatRepository.findByIdAndPlayersId(ID, ID))
        .thenReturn(chat);
    Mockito.when(chatMessageRepository.findByIdAndChatId(ID, ID))
        .thenReturn(Optional.of(chatMessage));
    Mockito.when(chatMessage.getPlayer())
        .thenReturn(player);
    Mockito.when(player.getId())
        .thenReturn(ID);

    // When
    chatMessageService.deleteChatMessage(ID, ID, ID);

    // Then
    Mockito.verify(chatMessageRepository, Mockito.times(1))
        .delete(chatMessage);
  }
}