package com.neighborhood.application.PlayerCareerHistory;

import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.domain.PlayerCareerHistory;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * A DTO for the {@link PlayerCareerHistory} entity
 */
@Data
public class PlayerCareerHistoryDto implements Serializable {

  private final Long id;
  private final PlayerDto player;
  @NotEmpty(message = "Team name required")
  private final String team;
  private final String league;
  private final Double pointsPerGame;
  private final Double assistsPerGame;
  private final Double reboundsPerGame;
  private final Double blocksPerGame;
  private final Double stealsPerGame;
  private final Double minutesPerGame;
}