package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.PlayerStreetStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStreetStatsRepository extends JpaRepository<PlayerStreetStats, Long> {

}