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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friendship")
public class Friendship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;
  private boolean accepted;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  @ManyToOne
  @JoinColumn(name = "friend_one_id")
  private Player friendOne;
  @ManyToOne
  @JoinColumn(name = "friend_two_id")
  private Player friendTwo;

}