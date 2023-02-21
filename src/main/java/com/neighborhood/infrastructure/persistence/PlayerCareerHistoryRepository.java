package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.PlayerCareerHistory;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCareerHistoryRepository extends JpaRepository<PlayerCareerHistory, Long> {

  Collection<PlayerCareerHistory> findByPlayerId(Long id);
}