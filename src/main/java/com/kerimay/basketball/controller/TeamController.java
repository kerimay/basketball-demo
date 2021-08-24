package com.kerimay.basketball.controller;

import com.kerimay.basketball.controller.dto.TeamDTO;
import com.kerimay.basketball.domain.Team;
import com.kerimay.basketball.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@Slf4j
@RequestMapping(value = "/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping()
    public TeamDTO createTeam(@RequestBody TeamDTO teamDTO) {
        return teamService.createTeam(teamDTO);
    }

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable int id) {
        return teamService.getTeamById(id);
    }

    @GetMapping()
    public List<Team> getTeams() {
        return teamService.getAllTeams();
    }

}
