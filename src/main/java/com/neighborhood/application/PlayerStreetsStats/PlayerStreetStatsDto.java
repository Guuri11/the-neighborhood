package com.neighborhood.application.PlayerStreetsStats;

import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.domain.PlayerStreetStats;
import java.io.Serializable;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * A DTO for the {@link PlayerStreetStats} entity
 */
@Data
public class PlayerStreetStatsDto implements Serializable {

  private final Long id;
  private final PlayerDto player;
  private final Boolean won;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private final Long points;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private final Long assists;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private final Long block;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private final Long rebounds;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private final Long steals;
  private final Boolean verified;
}