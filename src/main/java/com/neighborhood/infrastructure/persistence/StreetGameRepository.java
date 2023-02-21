package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.StreetGame.StreetGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetGameRepository extends JpaRepository<StreetGame, Long> {

}