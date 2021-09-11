package com.kerimay.basketball.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {

    @NotNull
    private int homeTeam;

    @NotNull
    private int awayTeam;

    @NotNull
    private int homeTeamScore;

    @NotNull
    private int awayTeamScore;

    @NotNull
    private int playerOfTheGame;
}
