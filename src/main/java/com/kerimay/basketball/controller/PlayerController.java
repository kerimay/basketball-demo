package com.kerimay.basketball.controller;

import com.kerimay.basketball.controller.dto.PlayerDTO;
import com.kerimay.basketball.controller.dto.SearchPlayerDTO;
import com.kerimay.basketball.domain.Player;
import com.kerimay.basketball.repository.PlayerSearchRepository;
import com.kerimay.basketball.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@Slf4j
@RequestMapping(value = "/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerSearchRepository playerSearchRepository;

    @PostMapping()
    public PlayerDTO createPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.createPlayer(playerDTO);
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @DeleteMapping("/{id}")
    public void deletePlayerById(@PathVariable int id) {
        playerService.deletePlayerById(id);
    }

    @PostMapping("playerPage")
    public Page<PlayerDTO> playerPage(@RequestBody SearchPlayerDTO searchPlayerDTO, Pageable pageable) {
        List<PlayerDTO> result = new ArrayList<>();
        Page<Player> players = playerSearchRepository.searchPlayers(searchPlayerDTO, pageable);
        if (players != null) {
            players.forEach(player -> result.add(PlayerDTO.convertToPlayerDTOFromPlayer(player)));
        }
        return new PageImpl<>(result,
                players != null ? players.getPageable() : Pageable.unpaged(),
                players != null ? players.getTotalElements() : 0L);
    }

}
