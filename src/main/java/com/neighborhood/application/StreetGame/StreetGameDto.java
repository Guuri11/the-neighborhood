package com.neighborhood.application.StreetGame;

import com.neighborhood.application.Court.CourtDto;
import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.domain.StreetGame.GameMode;
import com.neighborhood.domain.StreetGame.GameStatus;
import com.neighborhood.domain.StreetGame.StreetGame;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

/**
 * A DTO for the {@link StreetGame} entity
 */
@Data
public class StreetGameDto implements Serializable {

  private final Long id;
  private final Set<PlayerDto> players;
  private final GameMode gameMode;
  private final CourtDto court;
  private final LocalDateTime scheduledAt;
  private final Boolean accepted;
  private final GameStatus status;
}