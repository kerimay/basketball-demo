package com.kerimay.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kerimay.basketball.domain.enums.Conference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Conference conference;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @OneToOne
    private HeadCoach headCoach;

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<Player> players;

    private int winningRecordThisSeason;

    private int losingRecordThisSeason;
}
