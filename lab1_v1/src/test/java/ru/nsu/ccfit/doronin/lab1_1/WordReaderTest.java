package ru.nsu.ccfit.doronin.lab1_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class WordReaderTest {

    WordReader reader = null;

    @BeforeEach
    void setUp() {
        try {
            reader = new WordReader();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void readWord() {
        try (BufferedReader fileReader = new BufferedReader(
                new FileReader("src/test/java/ru/nsu/ccfit/doronin/lab1_1/test.txt"))) {
            assertNotNull(reader);
            assertTrue(reader.readWord(fileReader).equalsIgnoreCase("Success"));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}