package ru.nsu.ccfit.doronin.lab1_1;

import java.io.FileWriter;
import java.util.List;

//Класс для записи информации в файл
public class CSVWriter {

    public void run(List<Words> words) throws Exception {
        try (FileWriter writer = new FileWriter("result.csv", false)) {
            long countAllWords = 0;                          //Кол-во всех слов
            for (int i = 0; i < words.size(); ++i) {
                countAllWords += words.get(i).getCount();
            }
            System.out.println("Всего слов:" + countAllWords);
            for (int i = words.size() - 1; i >= 0; --i) {
                writer.write(words.get(i).getWord() + "," + words.get(i).getCount() + ", "
                        /*+ String.valueOf((((double) words.get(i).getCount() / countAllWords)) * 100)
                        + " %\n"*/);
                writer.write(String.format("%.5f",
                        (double) words.get(i).getCount() / countAllWords) + "%" + "\n");
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
