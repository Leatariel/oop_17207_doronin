package ru.nsu.ccfit.doronin.minesweeper.main;

//Класс, отвечающий за клетку на поле
public class Cell {

    private boolean open = false;  //открыта ячейка или нет
    private boolean bomb = false;  //Содержит ли бомбу
    private boolean flag = false;  //Висит ли на клетке флаг
    private int countNeighbourBomb = 0;   //Количество бомб, которые окружают клетку

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCountNeighbourBomb() {
        return countNeighbourBomb;
    }

    public void setCountNeighbourBomb(int countNeighbourBomb) {
        this.countNeighbourBomb = countNeighbourBomb;
    }

    //Сброс настроек, задать стандартные настройки
    public void setDefault(){
        open = false;
        bomb = false;
        flag = false;
        countNeighbourBomb = 0;
    }
}
