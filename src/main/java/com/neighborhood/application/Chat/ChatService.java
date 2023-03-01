package com.neighborhood.application.Chat;

import com.neighborhood.application.NotFoundException;
import com.neighborhood.application.mappers.ChatMapper;
import com.neighborhood.domain.Chat;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.infrastructure.persistence.ChatRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

  private final ChatRepository chatRepository;
  private final ChatMapper chatMapper;

  public ChatService(final ChatRepository chatRepository, final ChatMapper chatMapper) {

    this.chatRepository = chatRepository;
    this.chatMapper = chatMapper;
  }

  public Collection<ChatDto> allByPlayer(final Long id) {

    return chatRepository.findByPlayerId(id)
        .stream()
        .map(chatMapper::toDto)
        .collect(Collectors.toList());
  }

  public ChatDto oneById(final Long id, final Long playerId) {

    return chatRepository.findById(id)
        .map(f -> {
          if (checkAuthorization(f, playerId)) {
            throw new NotFoundException(id);
          }
          return f;
        })
        .map(chatMapper::toDto)
        .orElseThrow(() -> new NotFoundException(id));
  }

  public ChatDto create(final Chat chat) {
    // TODO: handle authorization
    chat.setCreatedAt(LocalDateTime.now());
    chat.setUpdatedAt(LocalDateTime.now());
    return chatMapper.toDto(chatRepository.save(chat));
  }

  public ChatDto update(final Chat chat, final Long id, final Long playerId) {

    final Chat existing = chatRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    if (checkAuthorization(chat, playerId)) {
      throw new NotFoundException(id);
    }
    existing.setUpdatedAt(LocalDateTime.now());

    return chatMapper.toDto(chatRepository.save(existing));
  }

  public void delete(final Long id, final Long playerId) {

    final Chat chat = chatRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(id));

    if (checkAuthorization(chat, playerId)) {
      throw new NotFoundException(id);
    }

    chatRepository.delete(chat);
  }

  private boolean checkAuthorization(final Chat chat, final Long playerId) {

    for (final Player p : chat.getPlayers()) {
      if (Objects.equals(p.getId(), playerId)) {
        return true;
      }
    }
    return false;

  }
}
