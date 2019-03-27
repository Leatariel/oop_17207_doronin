package ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events;


public class Event {
    private Observer observer;

    public void subscribe(Observer obj){
        observer = obj;
    }

    public void update(String command){
        observer.update(command);
    }

    public void update(String command, String arg){

    }
}
