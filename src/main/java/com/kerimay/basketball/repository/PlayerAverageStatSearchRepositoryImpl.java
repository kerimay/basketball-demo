package com.kerimay.basketball.repository;

import com.kerimay.basketball.domain.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PlayerAverageStatSearchRepositoryImpl implements PlayerAverageStatSearchRepository {

    @PersistenceContext
    private EntityManager em;

    private final PlayerRepository playerRepository;

    public PlayerAverageStats getAverageOfStatsByPlayer(int playerId) throws Exception {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QStat stat = QStat.stat;
        QPlayer player = QPlayer.player;
        QPlayerAverageStats playerAverageStats = QPlayerAverageStats.playerAverageStats;

        Optional<Player> player1 = playerRepository.getPlayerById(playerId);
        player1.orElseThrow(() -> new NullPointerException("Player not found"));

        JPAQuery<PlayerAverageStats> query = queryFactory
                .select(Projections.bean(
                        PlayerAverageStats.class,
                        player,
                        stat.playedMinutes.avg().as(playerAverageStats.averagePlayedMinutes),
                        stat.points.avg().as(playerAverageStats.averagePoints),
                        stat.rebounds.avg().as(playerAverageStats.averageRebounds),
                        stat.assists.avg().as(playerAverageStats.averageAssists),
                        stat.steals.avg().as(playerAverageStats.averageSteals),
                        stat.blocks.avg().as(playerAverageStats.averageBlocks),
                        stat.turnovers.avg().as(playerAverageStats.averageTurnovers),
                        stat.fieldGoalAttempt.avg().as(playerAverageStats.averageFieldGoalAttempt),
                        stat.fieldGoalMade.avg().as(playerAverageStats.averageFieldGoalMade),
                        stat.threePointAttempt.avg().as(playerAverageStats.averageThreePointAttempt),
                        stat.threePointMade.avg().as(playerAverageStats.averageThreePointMade),
                        stat.freeThrowAttempt.avg().as(playerAverageStats.averageFreeThrowAttempt),
                        stat.freeThrowMade.avg().as(playerAverageStats.averageFreeThrowMade)))
                .from(stat)
                .innerJoin(player).on(stat.player.id.eq(player.id))
                .where(player.id.eq(playerId));

        PlayerAverageStats avgStat = query.fetchOne();


        return PlayerAverageStats.builder()
                .player(avgStat.getPlayer())
                .averagePlayedMinutes(avgStat.getAveragePlayedMinutes())
                .averagePoints(avgStat.getAveragePoints())
                .averageRebounds(avgStat.getAverageRebounds())
                .averageAssists(avgStat.getAverageAssists())
                .averageSteals(avgStat.getAverageSteals())
                .averageBlocks(avgStat.getAverageBlocks())
                .averageFieldGoalAttempt(avgStat.getAverageFieldGoalAttempt())
                .averageFieldGoalMade(avgStat.getAverageFieldGoalMade())
                .averageFreeThrowAttempt(avgStat.getAverageFreeThrowAttempt())
                .averageFreeThrowMade(avgStat.getAverageFreeThrowMade())
                .averageThreePointAttempt(avgStat.getAverageThreePointAttempt())
                .averageThreePointMade(avgStat.getAverageThreePointMade())
                .averageTurnovers(avgStat.getAverageTurnovers())
                .build();
    }
}
