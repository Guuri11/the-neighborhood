package com.neighborhood.application.ChatMessage;

import com.neighborhood.application.NotFoundException;
import com.neighborhood.application.Notification.NotificationService;
import com.neighborhood.application.mappers.ChatMessageMapper;
import com.neighborhood.domain.Chat;
import com.neighborhood.domain.ChatMessage;
import com.neighborhood.domain.Notification.MessageType;
import com.neighborhood.domain.Notification.Notification;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.infrastructure.persistence.ChatMessageRepository;
import com.neighborhood.infrastructure.persistence.ChatRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

  @Autowired
  private ChatRepository chatRepository;

  @Autowired
  private ChatMessageMapper mapper;

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private ChatMessageRepository chatMessageRepository;

  public Collection<ChatMessageDto> findAllByChatIdAndPlayerId(final Long chatId, final Long playerId) {

    final Chat chat = chatRepository.findByIdAndPlayersId(chatId, playerId);
    if (chat == null) {
      throw new NotFoundException();
    }
    return chatMessageRepository.findByChatId(chatId)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  public ChatMessageDto create(final ChatMessage chatMessage) {

    chatMessage.setCreatedAt(LocalDateTime.now());
    chatMessage.setUpdatedAt(LocalDateTime.now());
    sendNotification(chatMessage);
    return mapper.toDto(chatMessageRepository.save(chatMessage));
  }

  // TODO: Test
  public ChatMessageDto updateChatMessage(final Long chatId, final Long playerId, final Long messageId,
      final ChatMessage chatMessage) {

    final Chat chat = chatRepository.findByIdAndPlayersId(chatId, playerId);
    if (chat == null) {
      throw new NotFoundException();
    }
    final Optional<ChatMessage> existingChatMessage = chatMessageRepository.findByIdAndChatId(messageId, chatId);
    if (existingChatMessage.isEmpty()) {
      throw new NotFoundException();
    }
    if (!existingChatMessage.get()
        .getPlayer()
        .getId()
        .equals(playerId)) {
      throw new NotFoundException();
    }
    existingChatMessage.get()
        .setMessage(chatMessage.getMessage());
    existingChatMessage.get()
        .setUpdatedAt(LocalDateTime.now());
    return mapper.toDto(chatMessageRepository.save(existingChatMessage.get()));
  }

  public void deleteChatMessage(final Long chatId, final Long playerId, final Long messageId) {

    final Chat chat = chatRepository.findByIdAndPlayersId(chatId, playerId);
    if (chat == null) {
      throw new NotFoundException();
    }
    final Optional<ChatMessage> chatMessage = chatMessageRepository.findByIdAndChatId(messageId, chatId);
    if (chatMessage.isEmpty()) {
      throw new NotFoundException();
    }
    if (!chatMessage.get()
        .getPlayer()
        .getId()
        .equals(playerId)) {
      throw new NotFoundException();
    }
    chatMessageRepository.delete(chatMessage.get());
  }

  private void sendNotification(final ChatMessage chatMessage) {

    final Notification notification = new Notification();
    notification.setMessage(MessageType.CHAT_MESSAGE);
    notification.setRead(false);
    notification.setCreatedAt(LocalDateTime.now());
    notification.setUpdatedAt(LocalDateTime.now());

    for (final Player player : chatMessage.getChat()
        .getPlayers()) {
      if (player == chatMessage.getPlayer()) {
        return;
      }
      notification.setPlayer(player);
      notificationService.create(notification);
    }
  }

}
