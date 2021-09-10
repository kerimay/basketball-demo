package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> getPlayerById(int id);

    @Query("select P from Player P")
    List<Player> getAllPlayers();

    @Modifying
    @Query("delete from Player P where P.id = ?1")
    void deletePlayerById(int id);
}
