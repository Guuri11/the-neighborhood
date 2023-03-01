package com.neighborhood.infrastructure.web.controller.socket;

import com.neighborhood.application.Chat.ChatService;
import com.neighborhood.application.ChatMessage.ChatMessageDto;
import com.neighborhood.application.ChatMessage.ChatMessageService;
import com.neighborhood.application.mappers.ChatMapper;
import com.neighborhood.application.mappers.ChatMessageMapper;
import com.neighborhood.domain.ChatMessage;
import com.neighborhood.domain.Player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageSocketController {

  @Autowired
  private ChatService chatService;

  @Autowired
  private ChatMessageService chatMessageService;

  @Autowired
  private ChatMessageMapper chatMessageMapper;

  @Autowired
  private ChatMapper chatMapper;

  @MessageMapping("/chat/send")
  @SendTo("/topic/{chatId}")
  public ChatMessageDto sendMessage(@AuthenticationPrincipal final Player player,
      @Payload final ChatMessage chatMessage) {

    chatMapper.toEntity(chatService.oneById(chatMessage.getChat()
        .getId(), player.getId()));

    chatMessageService.create(chatMessage);

    return chatMessageMapper.toDto(chatMessage);
  }
}