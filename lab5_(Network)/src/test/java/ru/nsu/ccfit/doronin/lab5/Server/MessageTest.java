package ru.nsu.ccfit.doronin.lab5.Server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    Message message;

    @BeforeEach
    void setUp() {
        message = new Message();
    }

    @Test
    void getDestination() {
        assertTrue(message.getDestination().equals("All"));
        message.setDestination("123");
        assertTrue(message.getDestination().equals("123"));
    }

    @Test
    void setDestination() {
        assertTrue(message.getDestination().equals("All"));
        message.setDestination("123");
        assertTrue(message.getDestination().equals("123"));
    }

    @Test
    void setText() {
        assertTrue(message.getText().equals(""));
        message.setText("test");
        assertTrue(message.getText().equals("test"));

    }

    @Test
    void getText() {
        assertTrue(message.getText().equals(""));
        message.setText("test");
        assertTrue(message.getText().equals("test"));
    }
}