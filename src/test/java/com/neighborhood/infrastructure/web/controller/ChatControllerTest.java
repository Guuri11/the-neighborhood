package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.Chat.ChatService;
import com.neighborhood.domain.Chat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

  final Long ID = 1L;
  @Mock
  ChatService ChatService;
  @Mock
  Chat chat;
  @Mock
  Authentication authentication;
  @InjectMocks
  ChatController chatController;

  @Test
  void all_shouldCallPlayerService() {
    // Given

    // When
    chatController.all(ID);

    // Then
    Mockito.verify(ChatService)
        .allByPlayer(ID);
  }

  @Test
  void delete_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    chatController.delete(authentication, ID);

    // Then
    Mockito.verify(ChatService)
        .delete(ID, ID);

  }
}