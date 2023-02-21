package com.neighborhood.application.Park;

import com.neighborhood.domain.Park;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * A DTO for the {@link Park} entity
 */
@Data
public class ParkDto implements Serializable {

  private final Long id;
  @NotEmpty(message = "Park name required")
  private final String name;
  @NotNull
  @NotEmpty(message = "Location required")
  private final String location;
}