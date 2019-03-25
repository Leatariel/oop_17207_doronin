package ru.nsu.ccfit.doronin.lab1_1;

public class Words implements Comparable<Words> {

    private String word;                //Слово
    private int count;                  //Количество слов в файле

    Words() {
        word = null;
        count = 0;
    }

    Words(String name) {
        word = name;
        count = 1;
    }

    Words(String name, int number) {
        word = name;
        count = number;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Words word) {
        return Integer.compare(count, word.getCount());
    }

    public void increaseCount() {
        ++count;
    }
}
