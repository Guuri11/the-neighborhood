package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.Chat;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRepository extends JpaRepository<Chat, Long> {

  @Query("SELECT c FROM Chat c JOIN c.players p WHERE p.id = :playerId")
  Collection<Chat> findByPlayerId(Long playerId);

  @Query("SELECT c FROM Chat c JOIN c.players p WHERE c.id = :id AND p.id = :playerId")
  Chat findByIdAndPlayersId(Long id, Long playerId);
}