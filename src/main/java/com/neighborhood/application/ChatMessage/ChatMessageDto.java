package com.neighborhood.application.ChatMessage;

import com.neighborhood.application.Chat.ChatDto;
import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.domain.ChatMessage;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * A DTO for the {@link ChatMessage} entity
 */
@Data
public class ChatMessageDto implements Serializable {

  private final Long id;
  private final ChatDto chat;
  private final PlayerDto player;
  private final String message;
  private final LocalDateTime updatedAt;
}