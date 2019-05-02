package ru.nsu.ccfit.doronin.lab5.Server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    History history;

    @BeforeEach
    void setUp() {
        history = new History();
    }

    @Test
    void addHistoryEl() {
        history.addHistoryEl("test");
        assertTrue(history.getHistory().contains("test"));
    }

    @Test
    void getHistory() {
        assertNotNull(history.getHistory());
    }

    @Test
    void getHistoryMessages() {
        assertTrue(history.getHistoryMessages().getText().equals(""));
        history.addHistoryEl("test");
        assertTrue(history.getHistoryMessages().getText().equals("History messages" + "\n"
        +"test\n" + "/...." + "\n"));
    }
}