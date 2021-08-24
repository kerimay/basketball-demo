package com.kerimay.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kerimay.basketball.domain.enums.Conference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Conference conference;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "team")
    private HeadCoach headCoach;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<Player> players;

    @Column(name = "win_count")
    private int winningRecordThisSeason;

    @Column(name = "lose_count")
    private int losingRecordThisSeason;
}
