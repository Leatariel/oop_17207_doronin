package ru.nsu.ccfit.doronin.lab1_1;

import java.io.BufferedReader;


//Класс для чтения слов из файла
public class WordReader {
    private StringBuilder word;       //Слово


    WordReader() throws Exception {
        try {
            word = new StringBuilder();
        } catch (Exception e) {
            System.err.println("*Ошибка* в конструкторе WordRead: " + e.getMessage());
            throw e;
        }
    }

    //Метод, который читает слово из файла и возвращает его
    //Если конец файла, то слово имеет длину 0
    public String readWord(BufferedReader reader) throws Exception {
        try {
            word.setLength(0);                                          //Очищаю слово
            int symbol = reader.read();                                 //Читаю символ
            while (-1 != symbol) {                                      //Пока не конец файла
                //Если буква или число, то добавляем в строку
                if (Character.isLetterOrDigit((char) symbol)) {
                    word.append((char) symbol);
                } else if (0 != word.length()) {
                    //Если символ-разделитель и в слове есть буквы, то выходим
                    break;
                }
                symbol = reader.read();                                 //Читаю символ
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
        return word.toString();
    }
}
