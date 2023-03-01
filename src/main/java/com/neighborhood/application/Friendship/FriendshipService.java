package com.neighborhood.application.Friendship;

import com.neighborhood.application.NotFoundException;
import com.neighborhood.application.Notification.NotificationService;
import com.neighborhood.application.mappers.FriendshipMapper;
import com.neighborhood.domain.Friendship;
import com.neighborhood.domain.Notification.MessageType;
import com.neighborhood.domain.Notification.Notification;
import com.neighborhood.infrastructure.persistence.FriendshipRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService {

  private final FriendshipRepository friendshipRepository;
  private final FriendshipMapper friendshipMapper;

  private final NotificationService notificationService;

  public FriendshipService(final FriendshipRepository friendshipRepository, final FriendshipMapper friendshipMapper,
      final NotificationService notificationService) {

    this.friendshipRepository = friendshipRepository;
    this.friendshipMapper = friendshipMapper;
    this.notificationService = notificationService;
  }

  public Collection<FriendshipDto> allByPlayer(final Long id) {

    return friendshipRepository.findByPlayerId(id)
        .stream()
        .map(friendshipMapper::toDto)
        .collect(Collectors.toList());
  }

  public FriendshipDto oneById(final Long id, final Long playerId) {

    return friendshipRepository.findById(id)
        .map(f -> {
          if (checkAuthorization(f, playerId)) {
            throw new NotFoundException(id);
          }
          return f;
        })
        .map(friendshipMapper::toDto)
        .orElseThrow(() -> new NotFoundException(id));
  }

  public FriendshipDto create(final Friendship friendship) {

    friendship.setAccepted(false);
    friendship.setCreatedAt(LocalDateTime.now());
    friendship.setUpdatedAt(LocalDateTime.now());
    friendshipRepository.save(friendship);
    sendNotification(friendship);
    return friendshipMapper.toDto(friendship);
  }

  public FriendshipDto update(final Friendship friendship, final Long id, final Long playerId) {

    final Friendship existing = friendshipRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    if (checkAuthorization(friendship, playerId)) {
      throw new NotFoundException(id);
    }
    existing.setUpdatedAt(LocalDateTime.now());
    existing.setAccepted(friendship.isAccepted());

    return friendshipMapper.toDto(friendshipRepository.save(existing));
  }

  public void delete(final Long id, final Long playerId) {

    final Friendship friendship = friendshipRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    if (checkAuthorization(friendship, playerId)) {
      throw new NotFoundException(id);
    }

    friendshipRepository.delete(friendship);
  }

  private void sendNotification(final Friendship friendship) {

    final Notification notification = new Notification();
    notification.setPlayer(friendship.getFriendTwo());
    notification.setMessage(MessageType.FRIENDSHIP);
    notification.setRead(false);
    notification.setCreatedAt(LocalDateTime.now());
    notification.setUpdatedAt(LocalDateTime.now());
    notificationService.create(notification);
  }

  private boolean checkAuthorization(final Friendship friendship, final Long playerId) {

    return !Objects.equals(friendship.getFriendOne()
        .getId(), playerId) || !Objects.equals(friendship.getFriendTwo()
        .getId(), playerId);
  }
}
