package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.ChatMessage;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

  Collection<ChatMessage> findByChatId(Long chatId);

  Optional<ChatMessage> findByIdAndChatId(Long id, Long chatId);
}