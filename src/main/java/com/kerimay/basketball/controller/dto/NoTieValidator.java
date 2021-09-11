package com.kerimay.basketball.controller.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoTieValidator implements ConstraintValidator<NoTie, GameDTO> {

    @Override
    public boolean isValid(GameDTO gameDTO,
                           ConstraintValidatorContext context) {
        return gameDTO.getHomeTeamScore() != gameDTO.getAwayTeamScore();
    }

}
