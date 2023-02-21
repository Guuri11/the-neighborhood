package com.neighborhood.domain;

import com.neighborhood.domain.Player.Player;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_career_history")
public class PlayerCareerHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private Player player;

  @NotEmpty(message = "Team name required")
  private String team;
  private String league;
  private Double points_per_game;
  private Double assists_per_game;
  private Double rebounds_per_game;
  private Double blocks_per_game;
  private Double steals_per_game;
  private Double minutes_per_game;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}