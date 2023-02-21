package com.neighborhood.application.Court;

import com.neighborhood.application.Park.ParkDto;
import com.neighborhood.domain.Court;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * A DTO for the {@link Court} entity
 */
@Data
public class CourtDto implements Serializable {

  private final Long id;
  private final ParkDto park;
  @NotEmpty(message = "Name required")
  private final String name;
}