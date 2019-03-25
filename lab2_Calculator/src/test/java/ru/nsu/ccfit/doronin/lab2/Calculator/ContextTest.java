package ru.nsu.ccfit.doronin.lab2.Calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {
    private Context context;

    @BeforeEach
    void setUp() {
        context = new Context();
    }

    @Test
    void pushToStack() {
        assertNotNull(context);
        context.pushToStack(5.0);
        assertEquals(5.0, context.popFromStack());
    }

    @Test
    void popFromStack() {
        assertNotNull(context);
        context.pushToStack(5.0);
        assertEquals(5.0, context.popFromStack());
    }

    @Test
    void setNameVar() {
        try {
            assertNotNull(context);
            context.setNameVar("a", 5.0);
            assertEquals(5.0, context.popFromNameVar("a"));
        } catch (Exception e){
            System.out.println("Ошибка: " + e);
        }
    }

    @Test
    void popFromNameVar() {
        try {
            assertNotNull(context);
            context.setNameVar("a", 6.0);
            assertEquals(6.0, context.popFromNameVar("a"));
        } catch (Exception e){
            System.out.println("Ошибка: " + e);
        }
    }
}