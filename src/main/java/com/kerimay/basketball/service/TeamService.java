package com.kerimay.basketball.service;

import com.kerimay.basketball.controller.dto.TeamDTO;
import com.kerimay.basketball.domain.Team;
import com.kerimay.basketball.domain.enums.Conference;
import com.kerimay.basketball.repository.HeadCoachRepository;
import com.kerimay.basketball.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final HeadCoachRepository headCoachRepository;

    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team newTeam = fromTeamDTO(teamDTO);
        teamRepository.save(newTeam);
        return teamDTO;
    }

    public Team getTeamById(int id) {
        Optional<Team> optionalTeam = teamRepository.getTeamById(id);
        return fromOptionalTeam(optionalTeam);
    }

    private Team fromTeamDTO(TeamDTO teamDTO) {
        return Team.builder()
                .name(teamDTO.getName())
                .conference(Conference.valueOf(teamDTO.getConference()))
                .headCoach(headCoachRepository.getHeadCoachByName(teamDTO.getHeadCoach()))
                .build();
    }

    private Team fromOptionalTeam(Optional<Team> optionalTeam) {
        return optionalTeam.orElseGet(teamRepository::getFreeAgent);
    }

    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }
}
