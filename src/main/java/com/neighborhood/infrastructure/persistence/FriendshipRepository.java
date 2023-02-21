package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

}