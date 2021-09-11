package com.kerimay.basketball.controller;

import com.kerimay.basketball.controller.dto.GameDTO;
import com.kerimay.basketball.domain.Game;
import com.kerimay.basketball.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@Slf4j
@RequestMapping(value = "/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping()
    public Game newGame(@RequestBody GameDTO gameDTO) throws Exception {
        return gameService.newGame(gameDTO);
    }

}