package ru.nsu.ccfit.doronin.minesweeper.main.GUI;

import ru.nsu.ccfit.doronin.minesweeper.main.Cell;
import ru.nsu.ccfit.doronin.minesweeper.main.Model;

import java.util.ArrayList;
import java.util.List;

public class ControllerGUI {
    Model model;
    ViewGUI view;

    private Long beginTime;
    private Long endTime;

    private boolean endGame = false;

    public ControllerGUI(Model model, ViewGUI view) {
        this.model = model;
        this.view = view;
        beginTime = System.currentTimeMillis();
    }

    //Получить сообщение автора игры
    public void showAuthorsMessage() {
        view.printAbout(model.getAuthorsMessage());
    }

    //Показать таблицу рекордов
    public void showScoreTable() {
        List<String> table = model.getScoreTable();
        int iter = 0;
        StringBuilder strForView = new StringBuilder();
        for (String str : table) {
            if (5 == iter) {
                break;
            }
            strForView.append(str + "\n");
            ++iter;
        }
        view.showScoreTable(strForView);
    }

    //Установить флаг
    public void setFlag(String index) {
        int height = model.getHeight();
        int width = model.getWidth();

        int number = Integer.parseInt(index);

        int h = number / height;
        int w = number - h * width;

        model.setFlag(h, w);

        printField();
    }

    //Установить новое имя игрока
    public void setNewName(String newName) {
        model.setName(newName);
    }

    //Игрок открывает клетку
    public void openCell(String index) {
        if(!endGame) {
            int height = model.getHeight();
            int width = model.getWidth();

            int number = Integer.parseInt(index);

            int h = number / height;
            int w = number - h * width;

            if (!model.openCell(h, w)) {
                view.showGameOverMessage();
                endGame = true;
            } else if (model.isWin()) {
                endTime = System.currentTimeMillis();
                model.addNewScore((endTime - beginTime) / 1000);
                model.writeScores();
                view.showWinMessage((endTime - beginTime) / 1000);
                endGame = true;
            }


            printField();
        }
    }

    //Напечатать поле
    private void printField() {
        int height = model.getHeight();
        int width = model.getWidth();

        List<String> viewField = new ArrayList<>();

        Cell[][] field = model.getField();

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {

                //Если закрыта
                if (!field[i][j].isOpen()) {
                    //Если стоит флаг
                    if (field[i][j].isFlag()) {
                        viewField.add("F");
                    } else {
                        viewField.add("x");
                    }
                    //Если открыта и бомба
                } else if (field[i][j].isBomb()) {
                    viewField.add("*");
                    //Открыта и без бомбы
                } else {
                    viewField.add(Integer.toString(field[i][j].getCountNeighbourBomb()));
                }
            }
        }
        view.showNewField(viewField);
    }

    //Установить уровень сложности
    public void setDifficult(String nameDiff){
        if(nameDiff.equalsIgnoreCase("easy")){
            model.setDifficultEasy();
        }else if(nameDiff.equalsIgnoreCase("normal")){
            model.setDifficultNormal();
        }else if(nameDiff.equalsIgnoreCase("hard")){
            model.setDifficultHard();
        }
        int h = model.getHeight();
        int w = model.getWidth();
        view.setNewDifficult(h * w, h, w, nameDiff);
        printField();
        beginTime = System.currentTimeMillis();
        endGame = false;
    }

    public void startNewGame(){
        model.reset();
        printField();
        beginTime = System.currentTimeMillis();
        endGame = false;
    }
}
