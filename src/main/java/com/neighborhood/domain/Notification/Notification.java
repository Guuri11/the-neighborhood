package com.neighborhood.domain.Notification;

import com.neighborhood.domain.Player.Player;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "notification")
public class Notification {

  private @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
  private MessageType message;
  @ManyToOne
  @JoinColumn(name = "player_id")
  private Player player;
  private boolean read;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
