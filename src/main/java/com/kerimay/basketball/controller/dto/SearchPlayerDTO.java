package com.kerimay.basketball.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPlayerDTO {

    private String name;
    private String lastName;
    private String nationality;
    private String teamName;
    private double averagePlayedMinutes;
    private double averagePoints;
    private double averageRebounds;
    private double averageAssists;
    private double averageSteals;
    private double averageBlocks;
    private double averageTurnovers;
    private double averageFieldGoalAttempt;
    private double averageFieldGoalMade;
    private double averageThreePointAttempt;
    private double averageThreePointMade;
    private double averageFreeThrowAttempt;
    private double averageFreeThrowMade;
}
