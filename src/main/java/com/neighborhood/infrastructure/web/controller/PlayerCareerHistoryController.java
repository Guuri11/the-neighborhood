package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.PlayerCareerHistory.PlayerCareerHistoryDto;
import com.neighborhood.application.PlayerCareerHistory.PlayerCareerHistoryService;
import com.neighborhood.domain.PlayerCareerHistory;
import com.neighborhood.infrastructure.persistence.PlayerRepository;
import java.util.Collection;
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
@RequestMapping("/api/v1/players_career_histories")
public class PlayerCareerHistoryController {

  private final PlayerCareerHistoryService playerCareerHistoryService;

  PlayerCareerHistoryController(final PlayerCareerHistoryService playerCareerHistoryService,
      final PlayerRepository playerRepository) {

    this.playerCareerHistoryService = playerCareerHistoryService;
  }

  @GetMapping("/{id}")
  public Collection<PlayerCareerHistoryDto> all(final Long id) {

    return playerCareerHistoryService.findByPlayer(id);
  }

  @PostMapping
  PlayerCareerHistoryDto create(@RequestBody final PlayerCareerHistory newPlayerCareerHistory,
      final Authentication authentication) {

    return playerCareerHistoryService.create(newPlayerCareerHistory, Long.parseLong(authentication.getName()));
  }

  @PutMapping
  PlayerCareerHistoryDto replace(@RequestBody final PlayerCareerHistory newPlayerCareerHistory,
      final Authentication authentication) {

    return playerCareerHistoryService.update(newPlayerCareerHistory, Long.parseLong(authentication.getName()));
  }

  @DeleteMapping
  ResponseEntity<?> delete(final Authentication authentication) {

    playerCareerHistoryService.delete(Long.parseLong(authentication.getName()));
    return ResponseEntity.noContent()
        .build();
  }
}
