package com.kerimay.basketball.controller.dto;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoTieValidator.class)
public @interface NoTie {

    String message() default "Game can't end with a tie in a basketball match.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
