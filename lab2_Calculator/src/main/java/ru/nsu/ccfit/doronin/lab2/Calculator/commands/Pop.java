package ru.nsu.ccfit.doronin.lab2.Calculator.commands;

import ru.nsu.ccfit.doronin.lab2.Calculator.CommandClass;
import ru.nsu.ccfit.doronin.lab2.Calculator.Context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/*@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {
    ArgType arg();
}

enum ArgType {
    CONTEXT, ARGS
}*/

public class Pop implements CommandClass {
    @Inject(arg = Inject.ArgType.CONTEXT)
    private Context context;

    @Inject(arg = Inject.ArgType.ARGS)
    private List<String> arg;

    @Override
    public void run(){
        if(1 == arg.size()) {
            context.popFromStack();
        }else{
            System.out.println("pop: неверное кол-во аргументов");
        }

    }
}
