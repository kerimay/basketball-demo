package com.kerimay.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @ManyToOne
    private Team homeTeam;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @ManyToOne
    private Team awayTeam;

    @NotNull
    private int homeTeamScore;

    @NotNull
    private int awayTeamScore;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @ManyToOne
    private Player playerOfTheGame;

    @NotNull
    private LocalDateTime timestamp;
}
