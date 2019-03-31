package ru.nsu.ccfit.doronin.minesweeper.main;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private Cell cell;

    @BeforeEach
    void setUp() {
    cell = new Cell();
    }

    @org.junit.jupiter.api.Test
    void isOpen() {
        assertNotNull(cell);
        assertEquals(false, cell.isOpen());
        cell.setOpen(true);
        assertEquals(true, cell.isOpen());
    }

    @org.junit.jupiter.api.Test
    void setOpen() {
        assertNotNull(cell);
        assertEquals(false, cell.isOpen());
        cell.setOpen(true);
        assertEquals(true, cell.isOpen());
    }

    @org.junit.jupiter.api.Test
    void isBomb() {
        assertNotNull(cell);
        assertEquals(false, cell.isBomb());
        cell.setBomb(true);
        assertEquals(true, cell.isBomb());
    }

    @org.junit.jupiter.api.Test
    void setBomb() {
        assertNotNull(cell);
        assertEquals(false, cell.isBomb());
        cell.setBomb(true);
        assertEquals(true, cell.isBomb());
    }

    @org.junit.jupiter.api.Test
    void isFlag() {
        assertNotNull(cell);
        assertEquals(false, cell.isFlag());
        cell.setFlag(true);
        assertEquals(true, cell.isFlag());
    }

    @org.junit.jupiter.api.Test
    void setFlag() {
        assertNotNull(cell);
        assertEquals(false, cell.isFlag());
        cell.setFlag(true);
        assertEquals(true, cell.isFlag());
    }

    @org.junit.jupiter.api.Test
    void getCountNeighbourBomb() {
        assertNotNull(cell);
        assertEquals(0, cell.getCountNeighbourBomb());
        cell.setCountNeighbourBomb(5);
        assertEquals(5, cell.getCountNeighbourBomb());
    }

    @org.junit.jupiter.api.Test
    void setCountNeighbourBomb() {
        assertNotNull(cell);
        assertEquals(0, cell.getCountNeighbourBomb());
        cell.setCountNeighbourBomb(5);
        assertEquals(5, cell.getCountNeighbourBomb());
    }

    @org.junit.jupiter.api.Test
    void setDefault() {
        assertNotNull(cell);
        cell.setOpen(true);
        cell.setFlag(true);
        cell.setBomb(true);
        cell.setCountNeighbourBomb(999);
        cell.setDefault();
        assertEquals(false, cell.isOpen());
        assertEquals(false, cell.isFlag());
        assertEquals(false, cell.isBomb());
        assertEquals(0, cell.getCountNeighbourBomb());
    }
}