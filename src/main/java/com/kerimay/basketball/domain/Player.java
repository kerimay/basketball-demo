package com.kerimay.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player extends Person {

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @ManyToOne
    private Team team;


    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<Stat> stats;
}
