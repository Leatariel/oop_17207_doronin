package ru.nsu.ccfit.doronin.minesweeper.main.textUI;


import ru.nsu.ccfit.doronin.minesweeper.main.Cell;
import ru.nsu.ccfit.doronin.minesweeper.main.Model;
import ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events.Observer;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class ControllerTextUI implements Observer {
    private Model model;
    private ViewTextUI view;

    public ControllerTextUI(Model model, ViewTextUI view) {
        this.model = model;
        this.view = view;
    }

    //Получить сообщение автора игры
    public String getAuthorsMessage() {
        return model.getAuthorsMessage();
    }

    public void setNamePlayer(String name) {
        model.setPlayerName(name);
    }

    //Получить поле
    public List<String> getField() {
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
    public boolean openCell(String numberStr) {
        int height = model.getHeight();
        int width = model.getWidth();

        int number = Integer.parseInt(numberStr);

        int h = number / height;
        int w = number - h * width;
        return model.openCell(h, w);
    }

    //Установить флаг на клетку
    public void setFlag(String numberStr) {
        int height = model.getHeight();
        int width = model.getWidth();

        int number = Integer.parseInt(numberStr);

        int h = number / height;
        int w = number - h * width;
        model.setFlag(h, w);
    }

    //Перезагрузить поле
    public void reset() {
        model.reset();
    }

    //Игра Окончена
    public void GameOver() {
        model.reset();
    }

    public void setDifficultEasy(){
        model.setDifficultEasy();
    }

    public void setDifficultNormal(){
        model.setDifficultNormal();
    }

    public void setDifficultHard(){
        model.setDifficultHard();
    }

    @Override
    public void update(String command){
        switch (command){
            case "easy":
                setDifficultEasy();
                break;
            case "normal":
                setDifficultNormal();
                break;
            case "hard":
                setDifficultHard();
                break;
            case "gameOver":
                GameOver();
                break;
            case "reset":
                reset();
                break;
            case "printField":
                break;
        }
    }

    @Override
    public void update(String command, String arg) {
        switch (command){
            case "openCell":
                openCell(arg);
                break;
            case "setFlag":
                setFlag(arg);
                break;
        }
    }
}
