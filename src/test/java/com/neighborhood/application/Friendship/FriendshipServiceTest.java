package com.neighborhood.application.Friendship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.neighborhood.application.mappers.FriendshipMapper;
import com.neighborhood.domain.Friendship;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.infrastructure.persistence.FriendshipRepository;
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
class FriendshipServiceTest {

  final Long ID = 1L;
  @Mock
  Friendship friendship;
  @Mock
  FriendshipDto friendshipDto;
  @Mock
  FriendshipRepository friendshipRepository;
  @Mock
  FriendshipMapper friendshipMapper;
  @Mock
  Player player;
  @InjectMocks
  FriendshipService friendshipService;

  @Test
  void allByPlayer() {
    // Given
    Mockito.when(friendshipRepository.findByPlayerId(ID))
        .thenReturn(List.of(friendship));
    Mockito.when(friendshipMapper.toDto(friendship))
        .thenReturn(friendshipDto);

    // When
    final Collection<FriendshipDto> result = friendshipService.allByPlayer(ID);

    // Then
    assertNotNull(result);
  }

  @Test
  void oneById() {
    // Given
    Mockito.when(friendshipRepository.findById(ID))
        .thenReturn(Optional.of(friendship));
    Mockito.when(friendship.getFriendOne())
        .thenReturn(player);
    Mockito.when(friendship.getFriendTwo())
        .thenReturn(player);
    Mockito.when(friendshipMapper.toDto(friendship))
        .thenReturn(friendshipDto);

    // When
    final FriendshipDto result = friendshipService.oneById(ID, player.getId());

    // Then
    assertEquals(result, friendshipDto);
  }

  @Test
  void create() {
    // Given
    Mockito.when(friendshipRepository.save(friendship))
        .thenReturn(friendship);
    Mockito.when(friendshipMapper.toDto(friendship))
        .thenReturn(friendshipDto);

    // When
    final FriendshipDto result = friendshipService.create(friendship);

    // Then
    assertEquals(result, friendshipDto);
  }

  @Test
  void update() {
    // Given
    Mockito.when(friendshipRepository.findById(ID))
        .thenReturn(Optional.of(friendship));
    Mockito.when(friendship.getFriendOne())
        .thenReturn(player);
    Mockito.when(friendship.getFriendTwo())
        .thenReturn(player);
    Mockito.when(friendshipRepository.save(friendship))
        .thenReturn(friendship);
    Mockito.when(friendshipMapper.toDto(friendship))
        .thenReturn(friendshipDto);
    // When
    final FriendshipDto result = friendshipService.update(friendship, ID, player.getId());

    // Then
    assertEquals(result, friendshipDto);
  }

  @Test
  void deleteTest() {
    // Given
    final Long id = 1L;
    final Friendship friendship = new Friendship();
    player.setId(id);
    friendship.setId(id);
    friendship.setFriendOne(player);
    friendship.setFriendTwo(player);
    Mockito.when(friendshipRepository.findById(id))
        .thenReturn(Optional.of(friendship));

    // When
    friendshipService.delete(id, player.getId());

    // Then
    Mockito.verify(friendshipRepository, Mockito.times(1))
        .delete(friendship);
  }
}