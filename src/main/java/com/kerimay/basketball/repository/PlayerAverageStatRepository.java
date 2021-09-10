package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.PlayerAverageStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerAverageStatRepository extends JpaRepository<PlayerAverageStats, Double> {

    Optional<PlayerAverageStats> findByPlayer_Id(int playerId);
}
