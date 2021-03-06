package com.kerimay.basketball.repository;

import com.kerimay.basketball.controller.dto.SearchPlayerDTO;
import com.kerimay.basketball.domain.Player;
import com.kerimay.basketball.domain.QPlayer;
import com.kerimay.basketball.domain.QPlayerAverageStats;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class PlayerSearchRepositoryImpl implements PlayerSearchRepository {

    @PersistenceContext
    private EntityManager em;

    QPlayer qPlayer = QPlayer.player;
    QPlayerAverageStats playerAverageStats = QPlayerAverageStats.playerAverageStats;

    public Page<Player> searchPlayers(SearchPlayerDTO searchPlayerDTO, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QPlayer player = QPlayer.player;
        QPlayerAverageStats qPlayerAverageStats = QPlayerAverageStats.playerAverageStats;

        List<BooleanBuilder> predicates = new ArrayList<>(populatePredicateList(searchPlayerDTO));

        JPAQuery<Player> query = queryFactory.select(player).from(player)
                .leftJoin(qPlayerAverageStats).on(qPlayerAverageStats.player.id.eq(player.id));
        query.where(predicates.toArray(new BooleanBuilder[]{}));

        if (pageable.getSort().isUnsorted()) {
            query.orderBy(player.name.asc());
        }
        //todo: addSorting

        long totalRows = query.fetchCount();
        query.offset((long) pageable.getPageNumber() * pageable.getPageSize());
        query.limit(pageable.getPageSize());
        List<Player> entries = query.fetch();

        return new PageImpl<>(entries, pageable, totalRows);
    }

    private List<BooleanBuilder> populatePredicateList(SearchPlayerDTO searchPlayerDTO) {
        List<BooleanBuilder> predicates = new ArrayList<>();

        if (searchPlayerDTO.getName() != null && !searchPlayerDTO.getName().isEmpty()) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(qPlayer.name.like(addWildcard(searchPlayerDTO.getName(), true, true))));
        }

        if (searchPlayerDTO.getLastName() != null && !searchPlayerDTO.getLastName().isEmpty()) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(qPlayer.lastName.like(addWildcard(searchPlayerDTO.getLastName(), true, true))));
        }

        if (searchPlayerDTO.getTeamName() != null && !searchPlayerDTO.getTeamName().isEmpty()) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(qPlayer.team.name.like(addWildcard(searchPlayerDTO.getTeamName(), true, true))));
        }

        if (searchPlayerDTO.getNationality() != null && !searchPlayerDTO.getNationality().isEmpty()) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(qPlayer.nationality.like(searchPlayerDTO.getNationality())));
        }

        // todo: make averages work

        if (searchPlayerDTO.getAveragePlayedMinutes() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averagePlayedMinutes.goe(searchPlayerDTO.getAveragePlayedMinutes())));
        }

        if (searchPlayerDTO.getAveragePoints() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averagePoints.goe(searchPlayerDTO.getAveragePoints())));
        }

        if (searchPlayerDTO.getAverageRebounds() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageRebounds.goe(searchPlayerDTO.getAverageRebounds())));
        }

        if (searchPlayerDTO.getAverageAssists() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageAssists.goe(searchPlayerDTO.getAverageAssists())));
        }

        if (searchPlayerDTO.getAverageSteals() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageSteals.goe(searchPlayerDTO.getAverageSteals())));
        }

        if (searchPlayerDTO.getAverageBlocks() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageBlocks.goe(searchPlayerDTO.getAverageBlocks())));
        }

        if (searchPlayerDTO.getAverageTurnovers() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageTurnovers.goe(searchPlayerDTO.getAverageTurnovers())));
        }

        if (searchPlayerDTO.getAverageFieldGoalAttempt() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageFieldGoalAttempt.goe(searchPlayerDTO.getAverageFieldGoalAttempt())));
        }

        if (searchPlayerDTO.getAverageFieldGoalMade() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageFieldGoalMade.goe(searchPlayerDTO.getAverageFieldGoalMade())));
        }

        if (searchPlayerDTO.getAverageFreeThrowAttempt() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageFreeThrowAttempt.goe(searchPlayerDTO.getAverageFreeThrowAttempt())));
        }

        if (searchPlayerDTO.getAverageFreeThrowMade() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageFreeThrowMade.goe(searchPlayerDTO.getAverageFreeThrowMade())));
        }

        if (searchPlayerDTO.getAverageThreePointAttempt() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageThreePointAttempt.goe(searchPlayerDTO.getAverageThreePointAttempt())));
        }
        if (searchPlayerDTO.getAverageThreePointMade() != 0) {
            BooleanBuilder builder = new BooleanBuilder();
            predicates.add(builder.and(playerAverageStats.averageThreePointMade.goe(searchPlayerDTO.getAverageThreePointMade())));
        }

        return predicates;
    }

    private String addWildcard(String value, boolean before, boolean after) {
        if (before) {
            value = "%" + value;
        }

        if (after) {
            value = value + "%";
        }

        return value;
    }
}
