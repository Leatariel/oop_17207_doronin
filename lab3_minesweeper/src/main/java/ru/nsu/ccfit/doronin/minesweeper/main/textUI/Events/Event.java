package ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events;


import ru.nsu.ccfit.doronin.minesweeper.main.textUI.ControllerTextUI;

public class Event implements Observer {
    private ControllerTextUI controller;

    public void subscribe(ControllerTextUI obj){
        controller = obj;
    }

    @Override
    public void update(String command){
        controller.update(command);
    }

    public void update(String command, String arg){
        controller.update(command, arg);
    }
}
