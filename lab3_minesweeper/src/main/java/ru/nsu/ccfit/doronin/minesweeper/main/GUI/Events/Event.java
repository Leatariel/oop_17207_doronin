package ru.nsu.ccfit.doronin.minesweeper.main.GUI.Events;

import ru.nsu.ccfit.doronin.minesweeper.main.GUI.ControllerGUI;
import ru.nsu.ccfit.doronin.minesweeper.main.textUI.ControllerTextUI;

public class Event implements Observer {

    private ControllerGUI controller;

    @Override
    public void subscribe(ControllerGUI obj){
        controller = obj;
    }

    @Override
    public void setDifficult(String nameDiff) {
        controller.setDifficult(nameDiff);
    }

    @Override
    public void showAbout(){
        controller.showAuthorsMessage();
    }

    @Override
    public void showScoreTable() {
        controller.showScoreTable();
    }

    @Override
    public void startNewGame() {
        controller.startNewGame();
    }

//    @Override
//    public void setDifficultEasy() {
//        controller.setDifficultEasy();
//    }
//
//    @Override
//    public void setDifficultNormal() {
//        controller.setDifficultNormal();
//    }
//
//    @Override
//    public void setDifficultHard() {
//        controller.setDifficultHard();
//    }

    @Override
    public void openCell(String index) {
        controller.openCell(index);
    }

    @Override
    public void setFlag(String index) {
        controller.setFlag(index);
    }

    @Override
    public void setNewName(String newName) {
        controller.setNewName(newName);
    }
}
