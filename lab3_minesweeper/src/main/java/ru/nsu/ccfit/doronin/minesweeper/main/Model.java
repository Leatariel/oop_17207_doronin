package ru.nsu.ccfit.doronin.minesweeper.main;

import java.util.List;

public class Model {
    public static final int MAX_HEIGHT = 30;
    public static final int MAX_WIDTH = 30;

    private int height = 10;                        //Высота поля
    private int width = 10;                         //Ширина поля

    private boolean firstRound = true;              //Раунд. Сколько раз игрок нажал на поле

    private int countBomb = 10;                     //Кол-во бомб на поле

    private String playerName = "default";          //Имя игрока

    private Cell field[][];                         //Поле с клетками

    private int maxCountFlag = 10;                  //Максимальное кол-во флагов для конкретного уровня сложности
    private int curCountFlag = 0;                   //Текущее кол-во установленных флагов

    private int timeForEnd = 10;

    ScoreWorker scoreTable;

    Model() {
        field = new Cell[MAX_HEIGHT][MAX_WIDTH];
        for (int i = 0; i < MAX_HEIGHT; ++i) {
            for (int j = 0; j < MAX_WIDTH; ++j) {
                field[i][j] = new Cell();
            }
        }
        scoreTable = new ScoreWorker();
    }

    //Получить текущую высоту поля
    public int getHeight() {
        return height;
    }

    //Задать текущую высоту поля
    public void setHeight(int height) {
        this.height = height;
    }

    public Cell[][] getField() {
        return field;
    }

    //Получить текущую ширину поля
    public int getWidth() {
        return width;
    }

    //Получить время, после окочнания которого игра будет завершена с проигрышом
    public int getTimeforEnd() {
        return timeForEnd;
    }

    //Задать текущую ширину поля
    public void setWidth(int width) {
        this.width = width;
    }

    //Задать имя игрока
    public void setPlayerName(String name) {
        playerName = name;
    }

    //Установить флаг на клетке
    public void setFlag(int h, int w) {
        if (field[h][w].isFlag()) {
            field[h][w].setFlag(false);
            --curCountFlag;
        } else {
            if (curCountFlag != maxCountFlag) {
                field[h][w].setFlag(true);
                ++curCountFlag;
            }
        }
    }

    //Установить имя игрока
    public void setName(String name){
        scoreTable.setNamePlayer(name);
    }

    //Дать таблицу с результатами
    public List<String> getScoreTable(){
     return scoreTable.getScoreTable();
    }

    //Записать время
    public void addNewScore(Long time){
        scoreTable.addNewScore(time);
    }

    //Записать таблицу результатов в файл
    public void writeScores(){
        scoreTable.writeScoreTableToFile();
    }

    //Пользователь открыл (нажал) клетку
    //Возвращает true, если всё хорошо, false - если бомба
    public boolean openCell(int h, int w) {
        if (!field[h][w].isFlag() && !field[h][w].isOpen()) {
            field[h][w].setOpen(true);
            //Если первое нажатие, расставляем бомбы
            if (firstRound) {
                setBombs(h, w);
                //Задать количество окружающих бомб для каждой клетки
                setCountNeighbourBombsForField();

                firstRound = false;
            }
            //Если в клетке бомба
            if (field[h][w].isBomb()) {
                openAllCells();
                return false;
            }
            if(0 == field[h][w].getCountNeighbourBomb()) {
                openEmptyCells(h, w);
            }
        }
        return true;
    }

    //Открывает все ячейки
    public void openAllCells() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                field[i][j].setOpen(true);
            }
        }
    }

    //Сбросить настройки всех клеток
    public void reset() {
        for (int i = 0; i < MAX_HEIGHT; ++i) {
            for (int j = 0; j < MAX_WIDTH; ++j) {
                field[i][j].setDefault();
            }
        }
        firstRound = true;
    }

    //Получить сообщение автора
    public String getAuthorsMessage() {
        String message = "Эта игра была разработана бедным студентом из группы 17207 ФИТ НГУ.\n"
                + "Идея игры была нагло скомунизжена автором с игры Сапёр для Windows.\n"
                + "Игра создавалась при финансовой, моральной, котлетной и гуляшной поддержке мамы автора.\n"
                + "Особую благодарность хочу высказать своим соседям по комнате, которые на протяжении всего\n"
                + "цикла разработки пытались свести автора в могилу своей игрой на гитаре, визгом и ужасным чувством юмора.\n"
                + "Все права успешно сворованы, приятной игры =)";
        return message;
    }

    //Раскидать по полю бомбы
    //currCell W H - коорд. клетки, которую нажали в первый раз и открыли. В ней бомбы всегда нет
    private void setBombs(int curCellH, int curCellW) {
        int counter = 0;
        int h = -1;
        int w = -1;
        while (counter < countBomb) {
            h = (int) (Math.random() * height);
            w = (int) (Math.random() * width);
            if (!((h == curCellH) && (w == curCellW)) && (!field[h][w].isBomb())) {
                field[h][w].setBomb(true);
                ++counter;
            }
        }
    }

    //Задать количество окружающих бомб для каждой клетки
    private void setCountNeighbourBombsForField() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                field[i][j].setCountNeighbourBomb(findCountNeighbourBombsForCell(i, j));
            }
        }
    }

    //Найти кол-во бомб для клетки, которые лежат рядом с ней
    private int findCountNeighbourBombsForCell(int H, int W) {

        int countNeighbourBombs = 0;


        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((H + i >= 0) && (H + i < height) && (W + j >= 0) && (W + j < width)) {
                    if (field[H + i][W + j].isBomb()) {
                        ++countNeighbourBombs;
                    }
                }
            }
        }

        return countNeighbourBombs;
    }

    //Открыть клетки, которые не содержат бомб
    private void openEmptyCells(int h, int w) {

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((h + i >= 0) && (h + i < height) && (w + j >= 0) && (w + j < width)) {
                    if (!field[h + i][w + j].isOpen() && !field[h + i][w + j].isBomb()) {
                        if (!field[h + i][w + j].isFlag()) {
                            field[h + i][w + j].setOpen(true);
                            //Если это пустая клетка, не число, то вызываю рекурсию
                            if (field[h + i][w + j].getCountNeighbourBomb() == 0) {
                                openEmptyCells(h + i, w + j);
                            }
                        }
                    }
                }
            }
        }
    }

    //Установить лёгкий уровень сложности
    public void setDifficultEasy() {
        reset();
        height = 10;
        width = 10;
        countBomb = 10;
    }

    //Установить лёгкий уровень сложности
    public void setDifficultNormal() {
        reset();
        height = 15;
        width = 15;
        countBomb = 50;
    }

    //Установить лёгкий уровень сложности
    public void setDifficultHard() {
        reset();
        height = 20;
        width = 20;
        countBomb = 100;
    }

    //Проверить, победа ли
    public boolean isWin(){
        int countBomb = 0;
        int countOpened = 0;
        for(int i = 0; i < height; ++i){
            for(int j = 0; j < width; ++j){
                if(field[i][j].isOpen()){
                    ++countOpened;
                } else if(field[i][j].isBomb()){
                    ++countBomb;
                }
            }
        }
        return ((countBomb + countOpened) == (height*width));
    }
}
