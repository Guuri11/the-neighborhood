package com.neighborhood.application.Chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.neighborhood.application.mappers.ChatMapper;
import com.neighborhood.domain.Chat;
import com.neighborhood.domain.Player.Player;
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
class ChatServiceTest {

  final Long ID = 1L;
  @Mock
  Chat chat;
  @Mock
  ChatDto chatDto;
  @Mock
  ChatRepository chatRepository;
  @Mock
  ChatMapper chatMapper;
  @Mock
  Player player;
  @InjectMocks
  ChatService chatService;

  @Test
  void allByPlayer() {
    // Given
    Mockito.when(chatRepository.findByPlayerId(ID))
        .thenReturn(List.of(chat));
    Mockito.when(chatMapper.toDto(chat))
        .thenReturn(chatDto);

    // When
    final Collection<ChatDto> result = chatService.allByPlayer(ID);

    // Then
    assertNotNull(result);
  }

  @Test
  void oneById() {
    // Given
    Mockito.when(chatRepository.findById(ID))
        .thenReturn(Optional.of(chat));
    Mockito.when(chatMapper.toDto(chat))
        .thenReturn(chatDto);

    // When
    final ChatDto result = chatService.oneById(ID, player.getId());

    // Then
    assertEquals(result, chatDto);
  }

  @Test
  void create() {
    // Given
    Mockito.when(chatRepository.save(chat))
        .thenReturn(chat);
    Mockito.when(chatMapper.toDto(chat))
        .thenReturn(chatDto);

    // When
    final ChatDto result = chatService.create(chat);

    // Then
    assertEquals(result, chatDto);
  }

  @Test
  void update() {
    // Given
    Mockito.when(chatRepository.findById(ID))
        .thenReturn(Optional.of(chat));
    Mockito.when(chatRepository.save(chat))
        .thenReturn(chat);
    Mockito.when(chatMapper.toDto(chat))
        .thenReturn(chatDto);
    // When
    final ChatDto result = chatService.update(chat, ID, player.getId());

    // Then
    assertEquals(result, chatDto);
  }

  @Test
  void deleteTest() {
    // Given
    final Long id = 1L;
    final Chat chat = new Chat();
    player.setId(id);
    chat.setId(id);
    Mockito.when(chatRepository.findById(id))
        .thenReturn(Optional.of(chat));

    // When
    chatService.delete(id, player.getId());

    // Then
    Mockito.verify(chatRepository, Mockito.times(1))
        .delete(chat);
  }
}