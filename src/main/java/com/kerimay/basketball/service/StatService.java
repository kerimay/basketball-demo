package com.kerimay.basketball.service;

import com.kerimay.basketball.controller.dto.StatDTO;
import com.kerimay.basketball.domain.Player;
import com.kerimay.basketball.domain.PlayerAverageStats;
import com.kerimay.basketball.domain.Stat;
import com.kerimay.basketball.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatService {

    private final PlayerRepository playerRepository;
    private final StatRepository statRepository;
    private final PlayerAverageStatRepository playerAverageStatRepository;
    private final PlayerAverageStatSearchRepository playerAverageStatSearchRepository;

    public Stat newStats(StatDTO statDTO) throws Exception {
        Player player = playerRepository.getPlayerById(statDTO.getPlayerId()).orElseThrow(() -> new Exception(""));
        Stat stat = StatDTO.fromStatDTOToStat(statDTO);
        stat.setPlayer(player);
        return statRepository.save(stat);
    }

    public PlayerAverageStats averageStat(int playerId) throws Exception {
        PlayerAverageStats playerAverageStats = playerAverageStatSearchRepository.getAverageOfStatsByPlayer(playerId);
        return playerAverageStatRepository.save(playerAverageStats);
    }
}
