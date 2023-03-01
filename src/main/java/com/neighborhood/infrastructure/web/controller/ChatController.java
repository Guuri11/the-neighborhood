package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.Chat.ChatDto;
import com.neighborhood.application.Chat.ChatService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController extends BaseController {

  private final ChatService chatService;

  @GetMapping
  public Collection<ChatDto> all(final Long id) {

    this.logger.info("Request: GET /chat by:{}", id.toString());
    return chatService.allByPlayer(id);
  }

  @GetMapping("/{id}")
  public ChatDto one(final Long id, final Authentication authentication) {

    this.logger.info("Request: GET /chat/{} by: {}", id.toString(), authentication.getName());
    return chatService.oneById(id, Long.parseLong(authentication.getName()));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> delete(final Authentication authentication, final Long id) {

    this.logger.info("Request: DELETE /chat/{} by {}", id.toString(), authentication.getName());
    chatService.delete(id, Long.parseLong(authentication.getName()));
    return ResponseEntity.noContent()
        .build();
  }
}
