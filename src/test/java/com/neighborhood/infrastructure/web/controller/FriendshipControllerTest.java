package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.Friendship.FriendshipService;
import com.neighborhood.domain.Friendship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class FriendshipControllerTest {

  final Long ID = 1L;
  @Mock
  FriendshipService FriendshipService;
  @Mock
  Friendship friendship;
  @Mock
  Authentication authentication;
  @InjectMocks
  FriendshipController friendshipController;

  @Test
  void all_shouldCallPlayerService() {
    // Given

    // When
    friendshipController.all(ID);

    // Then
    Mockito.verify(FriendshipService)
        .allByPlayer(ID);
  }

  @Test
  void create_shouldCallPlayerService() {
    // Given
    
    // When
    friendshipController.create(friendship);

    // Then
    Mockito.verify(FriendshipService)
        .create(friendship);

  }

  @Test
  void replace_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    friendshipController.replace(friendship, ID, authentication);

    // Then
    Mockito.verify(FriendshipService)
        .update(friendship, ID, ID);

  }

  @Test
  void delete_shouldCallPlayerService() {
    // Given
    Mockito.when(authentication.getName())
        .thenReturn("1");

    // When
    friendshipController.delete(authentication, ID);

    // Then
    Mockito.verify(FriendshipService)
        .delete(ID, ID);

  }
}