package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.Player.Player;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  Optional<Player> findByEmail(String email);

}