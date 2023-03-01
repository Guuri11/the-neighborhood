package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.ChatMessage.ChatMessageService;
import com.neighborhood.domain.ChatMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class ChatMessageControllerTest {

  final Long CHAT_ID = 1L;
  final Long MESSAGE_ID = 2L;
  final String USER_ID = "1";

  @Mock
  ChatMessageService chatMessageService;
  @Mock
  ChatMessage chatMessage;
  @Mock
  Authentication authentication;
  @InjectMocks
  ChatMessageController chatMessageController;

  @Test
  void all_shouldCallChatMessageService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn(USER_ID);

    // When
    chatMessageController.all(CHAT_ID, authentication);

    // Then
    Mockito.verify(chatMessageService)
        .findAllByChatIdAndPlayerId(CHAT_ID, Long.parseLong(USER_ID));
  }

  @Test
  void update_shouldCallChatMessageService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn(USER_ID);

    // When
    chatMessageController.update(CHAT_ID, MESSAGE_ID, authentication, chatMessage);

    // Then
    Mockito.verify(chatMessageService)
        .updateChatMessage(CHAT_ID, Long.parseLong(USER_ID), MESSAGE_ID, chatMessage);
  }

  @Test
  void delete_shouldCallChatMessageService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn(USER_ID);

    // When
    final ResponseEntity<?> responseEntity = chatMessageController.delete(CHAT_ID, MESSAGE_ID, authentication);

    // Then
    Mockito.verify(chatMessageService)
        .deleteChatMessage(CHAT_ID, Long.parseLong(USER_ID), MESSAGE_ID);
    Mockito.verifyNoMoreInteractions(chatMessageService);

    // Verify the response entity
    Mockito.verifyNoMoreInteractions(chatMessageService);
    assert responseEntity.getStatusCodeValue() == 204;
  }

}
