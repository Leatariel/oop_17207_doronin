package ru.nsu.ccfit.doronin.lab5.Client;

import ru.nsu.ccfit.doronin.lab5.Server.Message;

public class Controller {

    private Client client = null;
    private ClientGUI gui = null;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setGui(ClientGUI gui) {
        this.gui = gui;
    }

    //Отправить сообщение от gui клиенту
    public void sendMessage(String msg){
        client.sendMessages(msg);
    }

    public void printMessage(Message msg){
        gui.printMessageOnScreen(msg.getText());
    }

    public void closeClient(){
        client.sendMessages("@stop");
    }

    public void closeGUI(){
        gui.closeGUI();
    }

    public void setNicknameInGUI(String name){

        gui.setNickname(name);
    }
}
