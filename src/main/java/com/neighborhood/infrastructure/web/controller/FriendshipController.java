package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.Friendship.FriendshipDto;
import com.neighborhood.application.Friendship.FriendshipService;
import com.neighborhood.domain.Friendship;
import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/friendships")
public class FriendshipController {

  private final FriendshipService friendshipService;

  FriendshipController(final FriendshipService friendshipService) {

    this.friendshipService = friendshipService;
  }

  @GetMapping
  public Collection<FriendshipDto> all(final Long id) {

    return friendshipService.allByPlayer(id);
  }

  @GetMapping("/{id}")
  public FriendshipDto one(final Long id, final Authentication authentication) {

    return friendshipService.oneById(id, Long.parseLong(authentication.getName()));
  }

  @PostMapping
  FriendshipDto create(@RequestBody final Friendship friendship) {

    return friendshipService.create(friendship);
  }

  @PutMapping("/{id}")
  FriendshipDto replace(@RequestBody final Friendship friendship, final Long id,
      final Authentication authentication) {

    return friendshipService.update(friendship, id, Long.parseLong(authentication.getName()));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> delete(final Authentication authentication, final Long id) {

    friendshipService.delete(id, Long.parseLong(authentication.getName()));
    return ResponseEntity.noContent()
        .build();
  }
}
