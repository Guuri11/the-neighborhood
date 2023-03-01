package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.Player.PlayerDto;
import com.neighborhood.application.Player.PlayerService;
import java.util.Collection;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class PlayerController extends BaseController {

  private final PlayerService playerService;

  @GetMapping
  public Collection<PlayerDto> all() {

    this.logger.info("Request: GET /players");
    return playerService.all();
  }

  @GetMapping("/{id}")
  public PlayerDto one(@PathVariable final Long id) {

    this.logger.info("Request: GET /players/{}", id.toString());
    return playerService.one(id);
  }

  @GetMapping("/me")
  public PlayerDto me(final Authentication authentication) {

    this.logger.info("Requested: GET /players/me by {}", authentication.getName());
    return playerService.one(Long.parseLong(authentication.getName()));
  }

  @PutMapping
  PlayerDto replacePlayer(@Valid @RequestBody final PlayerDto playerDto, final Authentication authentication) {

    this.logger.info("Requested: PUT /players by {} with body {}", authentication.getName(), playerDto.toString());
    return playerService.update(playerDto, Long.parseLong(authentication.getName()));
  }

  @DeleteMapping
  ResponseEntity<?> deletePlayer(final Authentication authentication) {

    this.logger.info("Requested: DELETE /players by {}", authentication.getName());
    playerService.delete(Long.parseLong(authentication.getName()));
    return ResponseEntity.noContent()
        .build();
  }
}
