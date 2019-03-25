package ru.nsu.ccfit.doronin.lab1_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVWriterTest {

    private List<Words> listWords = null;
    private CSVWriter writer = null;
    private Words word = null;

    @BeforeEach
    void setUp() {
        listWords = new ArrayList<>();
        writer = new CSVWriter();
        word = new Words();
        word.setWord("Success");
        word.setCount(666);
        listWords.add(word);
    }

    @Test
    void run() {
        assertNotNull(listWords);

        try {
            writer.run(listWords);
        }catch (Exception e){
            System.out.println(e);
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader("result.csv"))) {
            String str = fileReader.readLine();

            assertTrue(str.equals("Success,666, 1,00000%"));

        } catch (Exception e) {
            System.err.println("*Ошибка*: " + e.getMessage());
        }
    }
}