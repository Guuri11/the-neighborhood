package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.PlayerStreetsStats.PlayerStreetStatsDto;
import com.neighborhood.application.PlayerStreetsStats.PlayerStreetStatsService;
import com.neighborhood.domain.PlayerStreetStats;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/players_street_stats")
@RequiredArgsConstructor
public class PlayerStreetStatsController extends BaseController {

  private final PlayerStreetStatsService playerStreetStatsService;

  @GetMapping("/{id}")
  public Collection<PlayerStreetStatsDto> all(final Long id) {

    this.logger.info("Request: GET /players_street_stats/{}", id.toString());
    return playerStreetStatsService.findByPlayer(id);
  }

  @PostMapping
  PlayerStreetStatsDto create(@RequestBody final PlayerStreetStats newPlayerStreetStats,
      final Authentication authentication) {

    this.logger.info("Request: POST /players_street_stats : {} by {}", newPlayerStreetStats.toString(),
        authentication.getName());
    return playerStreetStatsService.create(newPlayerStreetStats, Long.parseLong(authentication.getName()));
  }

  @PutMapping("/{id}")
  PlayerStreetStatsDto replace(@RequestBody final PlayerStreetStats updatedPlayerStreetStats, final Long id,
      final Authentication authentication) {

    this.logger.info("Request: PUT /players_street_stats/{} by: {} with body: {}", id.toString(),
        authentication.getName(), updatedPlayerStreetStats.toString());
    return playerStreetStatsService.update(updatedPlayerStreetStats, Long.parseLong(authentication.getName()), id);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> delete(final Authentication authentication, final Long id) {

    this.logger.info("Request: DELETE /players_street_stats/{} by: {}", id.toString(), authentication.getName());
    playerStreetStatsService.delete(id, Long.parseLong(authentication.getName()));
    return ResponseEntity.noContent()
        .build();
  }
}
