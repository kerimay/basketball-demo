package com.kerimay.basketball.controller.dto;

import com.kerimay.basketball.domain.Stat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatDTO {

    @NotNull
    private int playerId;

    @NotNull
    private int playedMinutes;

    @NotNull
    private int points;

    @NotNull
    private int rebounds;

    @NotNull
    private int assists;

    @NotNull
    private int steals;

    @NotNull
    private int blocks;

    @NotNull
    private int turnovers;

    @NotNull
    private int fieldGoalAttempt;

    @NotNull
    private int fieldGoalMade;

    @NotNull
    private int threePointAttempt;

    @NotNull
    private int threePointMade;

    @NotNull
    private int freeThrowAttempt;

    @NotNull
    private int freeThrowMade;

    public static Stat fromStatDTOToStat(StatDTO statDTO) {
        return Stat.builder()
                .playedMinutes(statDTO.getPlayedMinutes())
                .points(statDTO.getPoints())
                .assists(statDTO.getAssists())
                .rebounds(statDTO.getRebounds())
                .blocks(statDTO.getBlocks())
                .fieldGoalAttempt(statDTO.getFieldGoalAttempt())
                .fieldGoalMade(statDTO.getFieldGoalMade())
                .steals(statDTO.getSteals())
                .freeThrowAttempt(statDTO.getFreeThrowAttempt())
                .freeThrowMade(statDTO.getFreeThrowMade())
                .threePointAttempt(statDTO.getThreePointAttempt())
                .threePointMade(statDTO.getThreePointMade())
                .turnovers(statDTO.getTurnovers())
                .build();
    }
}
