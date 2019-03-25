package ru.nsu.ccfit.doronin.lab1_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {

    WordCounter counter = null;

    @BeforeEach
    void setUp() {
        try {
            counter = new WordCounter();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void run() {
        assertNotNull(counter);

        try {
            counter.run("src/test/java/ru/nsu/ccfit/doronin/lab1_1/test.txt");
        }catch (Exception e){
            System.out.println(e);
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader("result.csv"))) {
            String str = fileReader.readLine();

            assertTrue(str.equals("Success,1, 1,00000%"));

        } catch (Exception e) {
            System.err.println("*Ошибка*: " + e.getMessage());
        }


    }
}