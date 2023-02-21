package com.neighborhood.infrastructure.persistence;

import com.neighborhood.domain.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {

}