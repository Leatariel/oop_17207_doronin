package ru.nsu.ccfit.doronin.lab2.Calculator.commands;

import ru.nsu.ccfit.doronin.lab2.Calculator.CommandClass;
import ru.nsu.ccfit.doronin.lab2.Calculator.Context;

import java.util.List;

public class Define implements CommandClass {

    @Inject(arg = Inject.ArgType.CONTEXT)
    private Context context;

    @Inject(arg = Inject.ArgType.ARGS)
    private List<String> arg;

    @Override
    public void run() {
        if(arg.size() == 3){
            //Запоминаю переменную и значение
            context.setNameVar(arg.get(1), Double.parseDouble(arg.get(2)));
        }else{
            System.out.println("Define: неверное кол-во аргументов");
        }
    }
}
