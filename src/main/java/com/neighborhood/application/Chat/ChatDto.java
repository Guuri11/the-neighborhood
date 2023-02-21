package com.neighborhood.application.Chat;

import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.domain.Chat;
import java.io.Serializable;
import java.util.Collection;
import lombok.Data;

/**
 * A DTO for the {@link Chat} entity
 */
@Data
public class ChatDto implements Serializable {

  private final Long id;
  private final Collection<PlayerDto> players;
}