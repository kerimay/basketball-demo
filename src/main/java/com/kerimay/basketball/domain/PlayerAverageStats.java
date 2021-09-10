package com.kerimay.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAverageStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Player player;

    @NotNull
    @Column(name = "avg_played_minutes")
    private double averagePlayedMinutes;

    @NotNull
    @Column(name = "avg_points")
    private double averagePoints;

    @NotNull
    @Column(name = "avg_rebounds")
    private double averageRebounds;

    @NotNull
    @Column(name = "avg_assists")
    private double averageAssists;

    @NotNull
    @Column(name = "avg_steals")
    private double averageSteals;

    @NotNull
    @Column(name = "avg_blocks")
    private double averageBlocks;

    @NotNull
    @Column(name = "avg_turnovers")
    private double averageTurnovers;

    @NotNull
    @Column(name = "avg_field_goal_attempt")
    private double averageFieldGoalAttempt;

    @NotNull
    @Column(name = "avg_field_goal_made")
    private double averageFieldGoalMade;

    @NotNull
    @Column(name = "avg_three_point_attempt")
    private double averageThreePointAttempt;

    @NotNull
    @Column(name = "avg_three_point_made")
    private double averageThreePointMade;

    @NotNull
    @Column(name = "avg_free_throw_attempt")
    private double averageFreeThrowAttempt;

    @NotNull
    @Column(name = "avg_free_throw_made")
    private double averageFreeThrowMade;
}
