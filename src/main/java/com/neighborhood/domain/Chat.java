package com.neighborhood.domain;

import com.neighborhood.domain.Player.Player;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "chat")
public class Chat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @ManyToMany
  @JoinTable(name = "player_chats",
      joinColumns = @JoinColumn(name = "chats_id"),
      inverseJoinColumns = @JoinColumn(name = "player_id"))
  private Collection<Player> players = new ArrayList<>();

}