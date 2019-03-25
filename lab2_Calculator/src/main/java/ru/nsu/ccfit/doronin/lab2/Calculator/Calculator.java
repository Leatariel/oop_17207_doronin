package ru.nsu.ccfit.doronin.lab2.Calculator;

import org.apache.log4j.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Calculator {


    private static final Logger log = Logger.getLogger(Calculator.class);

    public static void main(String[] args) {

        //Строка из файла
        String commandStr;

        Mode mode = Mode.NORMAL;

        if(args[0].equalsIgnoreCase(Mode.DEBUG.name()))
        {
            mode = Mode.DEBUG;
        }

        if (2 == args.length) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(args[1]))) {
                while (null != (commandStr = fileReader.readLine())) {
                    runCommand(commandStr, mode);
                }

            } catch (Exception e) {
                System.err.println("*Ошибка*: " + e.getMessage());
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {

                    commandStr = reader.readLine();
                    //Для выхода вводим exit
                    if (commandStr.equalsIgnoreCase("e")) {
                        break;
                    }

                    runCommand(commandStr, mode);
                }
            } catch (Exception e) {
                System.err.println("*Ошибка*: " + e.getMessage());
            }
        }
    }

    //Создаёт и выполняет комманду
    private static void runCommand(String commandStr, Mode mode) {
        if (!commandStr.startsWith("#")) {
            try {

                //trim - убрать пробелы в начале и в конце строки
                //replaceAll - заменить послед. пробелов одним пробелом
                commandStr = commandStr.trim().replaceAll("\\s+", " ");

                //Разбиваю одну команду на несколько строк и заполняю в список из строк
                List<String> commandList = new ArrayList<String>(Arrays.asList(commandStr.split(" ")));

//                for(String c: commandList){
//                    System.out.println("*" + c);
//                }

                //Получаю объект с коммандой
                CommandClass cmd = Factory.createCommand(commandList, mode);


                if (null != cmd) {
                    cmd.run();
                }

            } catch (Exception e1) {
                System.out.println("*Ошибка во время выполнения*: " + e1);
            }
        }
    }
}