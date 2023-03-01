package com.neighborhood.infrastructure.web.controller;

import com.neighborhood.application.PlayerCareerHistory.PlayerCareerHistoryDto;
import com.neighborhood.application.PlayerCareerHistory.PlayerCareerHistoryService;
import com.neighborhood.domain.PlayerCareerHistory;
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
@RequestMapping("/api/v1/players_career_histories")
@RequiredArgsConstructor
public class PlayerCareerHistoryController extends BaseController {

  private final PlayerCareerHistoryService playerCareerHistoryService;

  @GetMapping("/{id}")
  public Collection<PlayerCareerHistoryDto> all(final Long id) {

    this.logger.info("Request: GET /players_career_histories/{}", id.toString());
    return playerCareerHistoryService.findByPlayer(id);
  }

  @PostMapping
  PlayerCareerHistoryDto create(@RequestBody final PlayerCareerHistory newPlayerCareerHistory,
      final Authentication authentication) {

    this.logger.info("Request: POST /players_career_histories : {} by {}", newPlayerCareerHistory.toString(),
        authentication.getName());
    return playerCareerHistoryService.create(newPlayerCareerHistory, Long.parseLong(authentication.getName()));
  }

  @PutMapping("/{id}")
  PlayerCareerHistoryDto replace(@RequestBody final PlayerCareerHistory updatedPlayerCareerHistory, final Long id,
      final Authentication authentication) {

    this.logger.info("Request: PUT /players_career_histories/{} by: {} with body: {}", id.toString(),
        authentication.getName(), updatedPlayerCareerHistory.toString());
    return playerCareerHistoryService.update(updatedPlayerCareerHistory, Long.parseLong(authentication.getName()), id);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> delete(final Authentication authentication, final Long id) {

    this.logger.info("Request: DELETE /players_career_histories/{} by: {}", id.toString(), authentication.getName());
    playerCareerHistoryService.delete(id, Long.parseLong(authentication.getName()));
    return ResponseEntity.noContent()
        .build();
  }
}
