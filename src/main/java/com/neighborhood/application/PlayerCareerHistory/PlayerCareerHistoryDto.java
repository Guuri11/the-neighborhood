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
  private final Double points_per_game;
  private final Double assists_per_game;
  private final Double rebounds_per_game;
  private final Double blocks_per_game;
  private final Double steals_per_game;
  private final Double minutes_per_game;
}