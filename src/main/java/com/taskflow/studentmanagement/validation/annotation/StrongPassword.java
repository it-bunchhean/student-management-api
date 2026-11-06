package com.taskflow.studentmanagement.validation.annotation;

import java.lang.annotation.*;

@Documented
//@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "password must be at leat 8 character long and contain one uppercase letter, one lower " +
            "case letter, one digit, and one special character";
}
