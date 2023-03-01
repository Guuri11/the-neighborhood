package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.ChatMessage.ChatMessageDto;
import com.neighborhood.application.ChatMessage.ChatMessageService;
import com.neighborhood.domain.ChatMessage;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat_messages")
@RequiredArgsConstructor
public class ChatMessageController extends BaseController {

  private final ChatMessageService chatMessageService;

  @GetMapping
  public Collection<ChatMessageDto> all(@RequestParam("chatId") final Long chatId,
      final Authentication authentication) {

    this.logger.info("Request: GET /chat_messages?chatId={} by {}", chatId.toString(), authentication.getName());
    return chatMessageService.findAllByChatIdAndPlayerId(chatId, Long.parseLong(authentication.getName()));
  }

  @PutMapping("/{id}")
  public ChatMessageDto update(@RequestParam("chatId") final Long chatId, final Long id,
      final Authentication authentication,
      @RequestBody final
      ChatMessage chatMessage) {

    this.logger.info("Request: POST /chat_messages/{}?chatId={} by{} with body{}", id, chatId, authentication.getName(),
        chatMessage.toString());
    return chatMessageService.updateChatMessage(chatId, Long.parseLong(authentication.getName()), id, chatMessage);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> delete(@RequestParam("chatId") final Long chatId, final Long id,
      final Authentication authentication) {

    this.logger.info("Request: DELETE /chat_messages/{} by {}", id.toString(), authentication.getName());
    chatMessageService.deleteChatMessage(chatId, Long.parseLong(authentication.getName()), id);
    return ResponseEntity.noContent()
        .build();
  }
}
