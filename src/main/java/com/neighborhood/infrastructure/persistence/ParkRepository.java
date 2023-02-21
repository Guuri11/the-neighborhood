package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.Park;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkRepository extends JpaRepository<Park, Long> {

}