package com.kerimay.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private double playedMinutes;

    @NotNull
    private double points;

    @NotNull
    private double rebounds;

    @NotNull
    private double assists;

    @NotNull
    private double steals;

    @NotNull
    private double blocks;

    @NotNull
    private double turnovers;

    @NotNull
    private double fieldGoalAttempt;

    @NotNull
    private double fieldGoalMade;

    @NotNull
    private double threePointAttempt;

    @NotNull
    private double threePointMade;

    @NotNull
    private double freeThrowAttempt;

    @NotNull
    private double freeThrowMade;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @ManyToOne
    private Player player;

    @NotNull
    private LocalDateTime timestamp;

    public Stat(int id, @NotNull double playedMinutes, @NotNull double points, @NotNull double rebounds, @NotNull double assists, @NotNull double steals, @NotNull double blocks, @NotNull double turnovers, @NotNull double fieldGoalAttempt, @NotNull double fieldGoalMade, @NotNull double threePointAttempt, @NotNull double threePointMade, @NotNull double freeThrowAttempt, @NotNull double freeThrowMade, Player player, @NotNull LocalDateTime timestamp) {
        this.id = id;
        this.playedMinutes = playedMinutes;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.fieldGoalAttempt = fieldGoalAttempt;
        this.fieldGoalMade = fieldGoalMade;
        this.threePointAttempt = threePointAttempt;
        this.threePointMade = threePointMade;
        this.freeThrowAttempt = freeThrowAttempt;
        this.freeThrowMade = freeThrowMade;
        this.player = player;
        this.timestamp = LocalDateTime.now();
    }

    public Stat() {
    }
}
