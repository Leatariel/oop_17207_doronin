package ru.nsu.ccfit.doronin.lab1_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordsTest {

    Words word;

    @BeforeEach
    void setUp() {
        word = new Words();
    }

    @Test
    void getWord() {
        assertNotNull(word);
        word.setWord("Hello");
        assertTrue(word.getWord().equals("Hello"));
    }

    @Test
    void setWord() {
        assertNotNull(word);
        word.setWord("Hello");
        assertTrue(word.getWord().equals("Hello"));
    }

    @Test
    void getCount() {
        assertNotNull(word);
        word.setCount(5);
        assertEquals(5, word.getCount());
    }

    @Test
    void setCount() {
        assertNotNull(word);
        word.setCount(5);
        assertEquals(5, word.getCount());
    }

    @Test
    void increaseCount() {
        assertNotNull(word);
        word.setCount(5);
        word.increaseCount();
        assertEquals(6, word.getCount());
    }
}