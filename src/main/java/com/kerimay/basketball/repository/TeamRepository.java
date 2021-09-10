package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> getTeamById(int id);

    @Query("select T from Team T " +
            "where T.name = 'Free Agent'")
    Team getFreeAgent();

    @Query("select T from Team T")
    List<Team> getAllTeams();

}
