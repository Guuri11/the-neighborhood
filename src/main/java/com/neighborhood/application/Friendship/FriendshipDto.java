package com.neighborhood.application.Friendship;

import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.domain.Friendship;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link Friendship} entity
 */
@Data
public class FriendshipDto implements Serializable {

  private final Long id;
  private final boolean accepted;
  private final PlayerDto friendOne;
  private final PlayerDto friendTwo;
}