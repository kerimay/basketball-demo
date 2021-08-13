package com.kerimay.basketball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class HeadCoach extends Person {

    @ToString.Exclude // avoid recursion
    @EqualsAndHashCode.Exclude // avoid recursion
    @JsonIgnore
    @ManyToOne
    private Team team;
}
