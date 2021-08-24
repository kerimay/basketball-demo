package com.kerimay.basketball.repository;

import com.kerimay.basketball.controller.dto.SearchPlayerDTO;
import com.kerimay.basketball.domain.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSearchRepository {
    Page<Player> searchPlayers(SearchPlayerDTO searchPlayerDTO, Pageable pageable);
}
