package com.neighborhood.application.Player;

import com.neighborhood.domain.Player.Archetype;
import com.neighborhood.domain.Player.Player;
import com.neighborhood.domain.Player.Position;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import lombok.Data;

/**
 * A DTO for the {@link Player} entity
 */
@Data
public class PlayerDto implements Serializable {

  private final Long id;
  @NotEmpty(message = "Name can't be empty")
  private final String name;
  @Email(message = "Email format wrong")
  @NotEmpty(message = "Email can't be empty")
  private final String email;
  private final String nickname;
  private final Byte height;
  private final Byte weight;
  @PastOrPresent(message = "Birthday not valid")
  private final LocalDateTime birthdate;
  @Min(message = "Min level is 0", value = 0)
  private final Long level;
  @Min(message = "Min experience is 0", value = 0)
  private final Long experience;
  private final Position position;
  private final Archetype archetype;
}