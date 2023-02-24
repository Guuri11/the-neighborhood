package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.Friendship;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

  @Query("SELECT f FROM Friendship f WHERE f.friendOne.id = :playerId OR f.friendTwo.id = :playerId")
  Collection<Friendship> findByPlayerId(Long playerId);
}