package ru.nsu.ccfit.doronin.lab2.Calculator.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inject {

    ArgType arg();

    enum ArgType{
        CONTEXT,
        ARGS
    }

}
