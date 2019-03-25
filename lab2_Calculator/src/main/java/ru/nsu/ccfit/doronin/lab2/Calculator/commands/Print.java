package ru.nsu.ccfit.doronin.lab2.Calculator.commands;

import ru.nsu.ccfit.doronin.lab2.Calculator.CommandClass;
import ru.nsu.ccfit.doronin.lab2.Calculator.Context;

import java.util.List;

public class Print implements CommandClass {

    @Inject(arg = Inject.ArgType.CONTEXT)
    private Context context;

    @Inject(arg = Inject.ArgType.ARGS)
    private List<String> arg;

    @Override
    public void run() {

        if(1 == arg.size()) {
            context.peekStack();
        } else{
            System.out.println("print: неверное кол-во аргументов");
        }
    }
}
