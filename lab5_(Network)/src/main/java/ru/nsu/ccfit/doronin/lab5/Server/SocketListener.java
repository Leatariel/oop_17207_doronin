package ru.nsu.ccfit.doronin.lab5.Server;

import org.apache.log4j.*;

import java.io.*;
import java.net.Socket;

class SocketListener extends Thread {

    private static final Logger log = Logger.getLogger(SocketListener.class);

    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private String nickname;
    private ObjectInputStream in;    // поток чтения из сокета
    private ObjectOutputStream out;  // поток чтения в сокет

    public SocketListener(Socket socket) throws Exception {
        this.socket = socket;
        socket.setSoTimeout(600000);

        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());

        nickname = "default";

        //Передаю историю сообщений
        out.writeObject(Server.history.getHistoryMessages());
        out.flush();
        start(); // вызываем run()
    }

    @Override
    public void run() {
        boolean socketIsOpen = true;
        try {
            Message message = null;
            message = (Message) in.readObject();
            nickname = message.getText();
            for (SocketListener vr : Server.serverList) {
                vr.send(new Message(nickname + " has been joined to session\n"));
            }
            if(Server.IS_LOG){
                log.info("User \""+nickname + "\" has been joined to session");
            }
            while (socketIsOpen) {

                message = (Message) in.readObject();

                if (message.getText().equals("@stop")) {
                    sendAll(new Message(nickname + " left this session\n"));
                    closeClient();
                    if(Server.IS_LOG){
                        log.info("User \""+nickname + "\": Command @stop");
                        log.info("User \""+nickname + "\" left this session");
                    }
                    break;
                } else if (message.getText().equals("@ls")) {
                    StringBuilder names = new StringBuilder();
                    names.append("User's List:\n");
                    for (SocketListener vr : Server.serverList) {
                        names.append(vr.getNickname() + "\n");
                    }
                    send(new Message(names.toString()));
                    if(Server.IS_LOG){
                        log.info("User \""+nickname + "\": Command @ls");
                    }

                } else {
                    Server.history.addHistoryEl(message.getText());
                    sendAll(message);
                }
            }
        } catch (Exception e) {
            sendAll(new Message(nickname + " left this session\n"));
            if(Server.IS_LOG){
                log.info("User \""+nickname + "\": Exception. " + e);
                log.info("User \""+nickname + "\" left this session");
            }
            this.closeClient();
            socketIsOpen = false;
        }
    }


    //Оправить сообщение клиенту
    private void send(Message msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException ignored) {
        }

    }

    //отправить сообщение всем
    private void sendAll(Message msg) {
        for (SocketListener vr : Server.serverList) {
            vr.send(msg);
        }
    }

    //остановить потоки ввода/вывода
    private synchronized void closeClient() {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
            in.close();
            out.close();
            for (SocketListener vr : Server.serverList) {
                if (vr.equals(this)) vr.interrupt();
            }
            Server.serverList.remove(this);
        } catch (IOException ignored) {
        }
    }

    private String getNickname() {
        return nickname;
    }
}

