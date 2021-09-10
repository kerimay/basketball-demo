package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.PlayerAverageStats;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerAverageStatSearchRepository {

    PlayerAverageStats getAverageOfStatsByPlayer(int playerId) throws Exception;
}
