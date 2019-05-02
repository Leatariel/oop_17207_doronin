package ru.nsu.ccfit.doronin.lab5.Server;

import java.io.Serializable;

public class Message implements Serializable {
    private String destination; //Кому посылают
    private String text;        //Текст сообщения

    public Message() {
        this.destination = "All";
        this.text = "";
    }

    public Message(String text) {
        this.destination = "All";
        this.text = text;
    }

    public Message(String destination, String text) {
        this.destination = destination;
        this.text = text;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
