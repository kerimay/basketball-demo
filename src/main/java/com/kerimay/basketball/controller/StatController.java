package com.kerimay.basketball.controller;

import com.kerimay.basketball.controller.dto.StatDTO;
import com.kerimay.basketball.domain.PlayerAverageStats;
import com.kerimay.basketball.domain.Stat;
import com.kerimay.basketball.service.StatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@Slf4j
@RequestMapping(value = "/stats")
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @PostMapping()
    public Stat newStats(@RequestBody StatDTO statDTO) throws Exception {
        return statService.newStats(statDTO);
    }

    @GetMapping(value = "average/{playerId}")
    public PlayerAverageStats getAverageStats(@PathVariable int playerId) throws Exception {
        return statService.averageStat(playerId);
    }

}