package ru.nsu.ccfit.doronin.lab1_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

//Класс для подсчёта слов из файла
public class WordCounter {
    private WordReader objReader;    //Объект, считывающий слова из файла

    WordCounter() throws Exception {
        try {
            objReader = new WordReader();
        } catch (Exception e) {
            System.err.println("*Ошибка* в конструкторе WordCounter: " + e.getMessage());
            throw e;
        }
    }

    //Заполняю массив словами и их количеством
    private void findCountWords(BufferedReader fileReader, Map<String, Integer> mapWords) throws Exception {
        try {
            String word = objReader.readWord(fileReader);
            while (0 != word.length()) {
/*                if (mapWords.containsKey(word)) {
                    mapWords.put(word, mapWords.get(word) + 1);
                } else {
                    mapWords.put(word, 1);
                }*/
                //Добавить значение в Map
                mapWords.merge(word, 1, (a, b) -> a + b);
                word = objReader.readWord(fileReader);
            }
        } catch (Exception e) {
            System.err.println("*Ошибка*. Класс WordCounter, метод findCountWords: " + e.getMessage());
            throw e;
        }
    }

    private void sortWords(List<Words> sortedWords, Map<String, Integer> mapWords) throws Exception {
        try {
            for (Map.Entry<String, Integer> obj : mapWords.entrySet()) {
                Words word = new Words(obj.getKey(), obj.getValue());
                sortedWords.add(word);
            }
//            TreeSet<Words> sorted = new TreeSet<>(sortedWords);
            Collections.sort(sortedWords);
        } catch (Exception e) {
            System.err.println("*Ошибка*. Класс WordCounter, метод run: " + e.getMessage());
            throw e;
        }
    }

    public void run(String fileName) throws Exception {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {

            //new HashMap
            Map<String, Integer> mapWords = new HashMap<String, Integer>();   //map из слов и их кол-ва

            findCountWords(fileReader, mapWords);

            List<Words> sortedWords = new ArrayList<Words>();

            sortWords(sortedWords, mapWords);

            CSVWriter writer = new CSVWriter();
            writer.run(sortedWords);

        } catch (Exception e) {
            System.err.println("*Ошибка*. Класс WordCounter, метод run: " + e.getMessage());
            throw e;
        }
    }
}
