package ru.nsu.ccfit.doronin.lab1_1;

public class Main {

    public static void main(String[] args) throws Exception {

        if (0 == args.length) {
            System.err.println("*Ошибка*. Не достаточно аргументов коммандной строки.");
            return;
        }

        try {
            WordCounter counter = new WordCounter();
            counter.run(args[0]);
        } catch (Exception e) {
            System.err.println("*Ошибка*: " + e.getMessage());
        }
    }
}