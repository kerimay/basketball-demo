package com.kerimay.basketball.service;

import com.kerimay.basketball.controller.dto.PlayerDTO;
import com.kerimay.basketball.domain.Player;
import com.kerimay.basketball.domain.Team;
import com.kerimay.basketball.repository.PlayerRepository;
import com.kerimay.basketball.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public Player getPlayerById(int id) throws Exception {
        return playerRepository.getPlayerById(id).orElseThrow(() -> new Exception("Player not found"));
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player newPlayer = fromPlayerDTO(playerDTO);
        playerRepository.save(newPlayer);
        return playerDTO;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.getAllPlayers();
    }

    public void updatePlayer(PlayerDTO playerDTO) {
        Player updatedPlayer = fromPlayerDTO(playerDTO);
        playerRepository.save(updatedPlayer);
    }

    public void deletePlayerById(int id) {
        playerRepository.deletePlayerById(id);
    }

    public Player fromPlayerDTO(PlayerDTO playerDTO) {
        Optional<Team> optionalTeam = teamRepository.getTeamById(playerDTO.getTeamId());
        return Player.builder()
                .name(playerDTO.getName())
                .lastName(playerDTO.getLastName())
                .nationality(playerDTO.getNationality())
                .team(optionalTeam.orElse(Team.builder().name("Free Agent").build()))
                .build();
    }
}
