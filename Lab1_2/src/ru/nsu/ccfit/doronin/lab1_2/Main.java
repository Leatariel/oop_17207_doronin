package ru.nsu.ccfit.doronin.lab1_2;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws Exception {

        if (0 == args.length) {
            System.err.println("*Ошибка*. Не достаточно аргументов коммандной строки.");
            return;
        }

        try (Stream<String> stream = Files.lines(Paths.get(args[0]));
             FileWriter writer = new FileWriter("result.csv", false)) {
            long[] counter = new long[1];
//            AtomicInteger counter2 = new AtomicInteger(0);
            //Получаю из файла список слов и их колличество
            Map<String, Long> listWords = stream
                    //Разбиваю 1 строку на много строк, в каждом - слово.
                    .map(w -> w.split("[^\\pL\\p{N}]+"))                //^0-9a-zA-Zа-я-А-Я-ёЁ
                    //Преобразовываю 1 строку в несколько (нужен из-за split)
                    .flatMap(Arrays::stream)
                    //Удаляю строки длины == 0. Из-за работы split образуются такие строки
                    .filter(str -> 0 != str.length())
//                    .peek(word -> { coutner[0]++; counter2.incrementAndGet(); })
                    .peek(word -> counter[0]++)
                    //Собираю все строки в Map. Вместе с этим считаю кол-во повторений слов
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            listWords
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEach(e -> {
                        try {
                            writer.write(e.getKey() + "," + e.getValue() + ", ");
                            writer.write(String.format("%.3f", (((double) e.getValue() / counter[0])) * 100)
                                    + "%\n");
                        } catch (Exception er) {
                            System.out.println("*Ошибка при записи в файл*: " + er);
                            throw new RuntimeException(er);
                        }
                    });
            System.out.println("Всего слов:" + counter[0]);
        } catch (Exception e) {
            System.out.println("*Ошибка*: " + e);
        }


    }
}
