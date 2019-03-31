package ru.nsu.ccfit.doronin.minesweeper.main.textUI;


import ru.nsu.ccfit.doronin.minesweeper.main.Cell;
import ru.nsu.ccfit.doronin.minesweeper.main.Model;
import ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events.Observer;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerTextUI {
    private Model model;
    private ViewTextUI view;
    private Long beginTime;
    private Long endTime;

    public ControllerTextUI(Model model, ViewTextUI view) {
        this.model = model;
        this.view = view;
    }

    //Запустить таймер
    private void startTimer() {
        beginTime = System.currentTimeMillis();
    }

    private void finishTimer() {
        endTime = System.currentTimeMillis();
    }

    //Получить сообщение автора игры
    private void showAuthorsMessage() {
        view.printAbout(model.getAuthorsMessage());
    }

    private void setNamePlayer(String name) {
        model.setPlayerName(name);
    }

    //Получить поле
    private List<String> getField() {
        Cell[][] field = model.getField();
        List<String> viewField = new ArrayList<>();

        int height = model.getHeight();
        int width = model.getWidth();

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
            viewField.add("\n");
        }

        return viewField;
    }

    //Открыть клетку
    private boolean openCell(String numberStr) {
        int height = model.getHeight();
        int width = model.getWidth();

        int number = Integer.parseInt(numberStr);

        int h = number / height;
        int w = number - h * width;
        return model.openCell(h, w);
    }

    //Установить флаг на клетку
    private void setFlag(String numberStr) {
        int height = model.getHeight();
        int width = model.getWidth();

        int number = Integer.parseInt(numberStr);

        int h = number / height;
        int w = number - h * width;
        model.setFlag(h, w);
    }

    //Перезагрузить поле
    private void reset() {
        model.reset();
    }

    private void setDifficultEasy() {
        model.setDifficultEasy();
    }

    private void setDifficultNormal() {
        model.setDifficultNormal();
    }

    private void setDifficultHard() {
        model.setDifficultHard();
    }

    //Записать новое время
    private void updateScore() {
        finishTimer();
        model.addNewScore((endTime - beginTime) / 1000);
    }

    public void update(String command) {
        switch (command) {
            case "new":
                startTimer();
                break;
            case "easy":
                setDifficultEasy();
                break;
            case "normal":
                setDifficultNormal();
                break;
            case "hard":
                setDifficultHard();
                break;
            case "reset":
                reset();
                break;
            case "about":
                showAuthorsMessage();
                break;
            case "printField":
                view.printField(getField());
                break;
            case "score":
                view.printScoreTable(model.getScoreTable());
                break;
            case "exit":
                updateScore();
                model.reset();
                break;
            case "closeGame":
                model.writeScores();
                break;
        }
    }

    public void update(String command, String arg) {
        switch (command) {
            case "open":
                //Если бомба
                if (!openCell(arg)) {
                    view.gameOver();
                    view.printField(getField());
                    model.reset();

                } else if (model.isWin()) {
                    updateScore();
                    model.addNewScore((endTime - beginTime) / 1000);
                    model.writeScores();
                    view.showWinMessage((endTime - beginTime) / 1000);
                    view.printField(getField());
                    model.reset();
                }
                break;
            case "flag":
                setFlag(arg);
                break;
            case "name":
                model.setName(arg);
                break;
        }
    }

}
