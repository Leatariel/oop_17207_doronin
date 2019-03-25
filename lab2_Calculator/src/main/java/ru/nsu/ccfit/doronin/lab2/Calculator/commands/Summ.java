package ru.nsu.ccfit.doronin.lab2.Calculator.commands;

import ru.nsu.ccfit.doronin.lab2.Calculator.CommandClass;
import ru.nsu.ccfit.doronin.lab2.Calculator.Context;

import java.util.List;

public class Summ implements CommandClass {

    @Inject(arg = Inject.ArgType.CONTEXT)
    private Context context;

    @Inject(arg = Inject.ArgType.ARGS)
    private List<String> arg;

    @Override
    public void run() {

        if (1 == arg.size()) {
            try {
                Double a = context.popFromStack();
                Double b = context.popFromStack();
                context.pushToStack(a + b);
            } catch (Exception e) {
                System.out.println("Ошибка во время поиска суммы: " + e);
            }
        } else {
            System.out.println("summ: неверное кол-во аргументов");
        }

    }
}
