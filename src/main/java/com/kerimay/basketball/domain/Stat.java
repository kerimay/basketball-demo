package com.kerimay.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stat {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private double playedMinutes;

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

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @ManyToOne
    private Player player;

}
