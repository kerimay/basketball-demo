package com.kerimay.basketball.service;

import com.kerimay.basketball.controller.dto.StatDTO;
import com.kerimay.basketball.domain.Player;
import com.kerimay.basketball.domain.Stat;
import com.kerimay.basketball.repository.PlayerRepository;
import com.kerimay.basketball.repository.StatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatService {

    private final PlayerRepository playerRepository;
    private final StatRepository statRepository;

    public Stat newStats(StatDTO statDTO) throws Exception {
        Player player = playerRepository.getPlayerById(statDTO.getPlayerId()).orElseThrow(() -> new Exception(""));
        Stat stat = StatDTO.fromStatDTOToStat(statDTO);
        stat.setPlayer(player);
        return statRepository.save(stat);
    }

    public List<Stat> getStatsByPlayerId(int id) {
        return statRepository.getStatsByPlayer_Id(id);
    }
}
