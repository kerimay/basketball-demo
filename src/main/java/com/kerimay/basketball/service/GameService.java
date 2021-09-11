package com.kerimay.basketball.service;

import com.kerimay.basketball.controller.dto.GameDTO;
import com.kerimay.basketball.domain.Game;
import com.kerimay.basketball.domain.Player;
import com.kerimay.basketball.domain.Team;
import com.kerimay.basketball.repository.GameRepository;
import com.kerimay.basketball.repository.PlayerRepository;
import com.kerimay.basketball.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public Game newGame(GameDTO gameDTO) {
        updateTeamsByWinAndLose(gameDTO);
        return gameRepository.save(Game.builder()
                .homeTeam(getTeam(gameDTO.getHomeTeam()))
                .awayTeam(getTeam(gameDTO.getAwayTeam()))
                .homeTeamScore(gameDTO.getHomeTeamScore())
                .awayTeamScore(gameDTO.getAwayTeamScore())
                .playerOfTheGame(getPlayer(gameDTO.getPlayerOfTheGame()))
                        .timestamp(LocalDateTime.now())
                .build());
    }

    public void updateTeamsByWinAndLose(GameDTO gameDTO) {
        Team homeTeam = getTeam(gameDTO.getHomeTeam());
        Team awayTeam = getTeam(gameDTO.getAwayTeam());

        if (gameDTO.getHomeTeamScore() > gameDTO.getAwayTeamScore()) {
            int wins = homeTeam.getWinningRecordThisSeason();
            int newWins = wins + 1;
            homeTeam.setWinningRecordThisSeason(newWins);

            int losses = awayTeam.getLosingRecordThisSeason();
            int newLoss = losses + 1;
            awayTeam.setLosingRecordThisSeason(newLoss);
        } else {
            int wins = awayTeam.getWinningRecordThisSeason();
            int newWins = wins + 1;
            awayTeam.setWinningRecordThisSeason(newWins);

            int losses = homeTeam.getLosingRecordThisSeason();
            int newLoss = losses + 1;
            homeTeam.setLosingRecordThisSeason(newLoss);
        }
        teamRepository.save(homeTeam);
        teamRepository.save(awayTeam);
    }

    private Team getTeam(int teamId) {
        return teamRepository.getTeamById(teamId).orElseThrow(() -> new NullPointerException("Team not found"));
    }

    private Player getPlayer(int playerId) {
        return playerRepository.getPlayerById(playerId).orElseThrow(() -> new NullPointerException("Player not found"));
    }
}
