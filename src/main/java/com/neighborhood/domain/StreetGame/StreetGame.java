package com.neighborhood.domain.StreetGame;

import com.neighborhood.domain.Court;
import com.neighborhood.domain.Player.Player;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "street_game")
public class StreetGame {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @ManyToMany(mappedBy = "streetGames")
  private Set<Player> players = new LinkedHashSet<>();

  @Enumerated(EnumType.STRING)
  private GameMode gameMode;

  @ManyToOne
  @JoinColumn(name = "court_id")
  private Court court;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime scheduledAt;
  private Boolean accepted;
  @Enumerated(EnumType.STRING)
  private GameStatus status;

}