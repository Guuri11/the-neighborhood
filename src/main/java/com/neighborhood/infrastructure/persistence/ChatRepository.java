package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}