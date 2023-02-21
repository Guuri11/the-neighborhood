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
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_street_stats")
public class PlayerStreetStats {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private Player player;
  private Boolean won;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private Long points;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private Long assists;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private Long block;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private Long rebounds;
  @PositiveOrZero(message = "Value has to be 0 or more")
  private Long steals;
  private Boolean verified;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}