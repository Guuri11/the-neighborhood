package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.PlayerStreetStats;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStreetStatsRepository extends JpaRepository<PlayerStreetStats, Long> {

  Collection<PlayerStreetStats> findByPlayerId(Long id);
}