package ru.nsu.ccfit.doronin.lab5.Client;

import ru.nsu.ccfit.doronin.lab5.Server.Message;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


class Client {

    private Socket socket;
    private BufferedReader inputUser;   // поток чтения с консоли
    private ObjectInputStream in;    // поток чтения из сокета
    private ObjectOutputStream out;// поток чтения в сокет
    private String addr; // ip адрес клиента
    private int port; // порт соединения
    private String nickname; // имя клиента
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;

    private Controller controller;

    private ReadMessage reader;


    public Client(String addr, int port, Controller contr) {
        this.addr = addr;
        this.port = port;
        try {
            controller = contr;
            socket = new Socket(addr, port);
            // потоки чтения из сокета / записи в сокет, и чтения с консоли
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            nickname = null;
            reader = new ReadMessage();
            reader.start(); // Поток, для чтения сообщений от сервера
        } catch (Exception e) {
            Client.this.closeClient();
        }

    }

    //Задать ник клиента и отправить серверу
    public void setNickname(String name) {
        try {
            nickname = name;
            out.writeObject(new Message(nickname));
            out.flush();
            controller.setNicknameInGUI(name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Закрываем все потоки
    public void closeClient() {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
            in.close();
            out.close();
            inputUser.close();
        } catch (IOException ignored) {
        }
    }

    // Поток, для чтения сообщений от сервера
    private class ReadMessage extends Thread {
        @Override
        public void run() {

            Message message = null;

            while (true) {
                try {
                    if (!this.isInterrupted()) {
                        message = (Message) in.readObject();
                        if (message.getText().equals("stop")) {
                            Client.this.closeClient();
                            break;
                        }
                        controller.printMessage(message);
//                        System.out.println(message.getText());
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("CLOSED!!!");
                    controller.closeGUI();
                    Client.this.closeClient();
                    this.interrupt();
                    break;
                }
            }
        }
    }


    //Посылаем сообщения на сервер
    public void sendMessages(String message) {
        if (nickname == null) {
            setNickname(message);
        } else {
            try {
                time = new Date(); // текущая дата
                dt1 = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
                dtime = dt1.format(time); // время

                if (message.equals("@stop")) {
                    out.writeObject(new Message("@stop"));
                    Client.this.closeClient();
                } else if (message.equals("\n@ls")) {
                    out.writeObject(new Message("@ls"));
                } else {
                    Thread.sleep(5000);
                    for (int i = 0; i < 5000; ++i) {
                        // отправляем на сервер
                        out.writeObject(new Message("(" + dtime + ") " + nickname + ": " + message + "\n"));
                    }
                }
                out.flush(); // чистим
            } catch (Exception e) {
                Client.this.closeClient();

            }
        }
    }

    /*    // Поток, посылающий сообщения на сервер
    private class SendMessage extends Thread {

        @Override
        public void run() {

            while (true) {
                if (!this.isInterrupted()) {
                    try {
                        String userWord;

                        time = new Date(); // текущая дата
                        dt1 = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
                        dtime = dt1.format(time); // время

                        userWord = inputUser.readLine(); // сообщения с консоли
                        if (userWord.equals("@stop")) {
                            out.writeObject(new Message("@stop"));
                            Client.this.closeClient();
                            break; // выходим из цикла если пришло "stop"
                        } else if (userWord.equals("@ls")) {
                            out.writeObject(new Message("@ls"));
                        } else {
                            // отправляем на сервер
                            out.writeObject(new Message("(" + dtime + ") " + nickname + ": " + userWord + "\n"));
                        }
                        out.flush(); // чистим
                    } catch (Exception e) {
                        try {
                            out.writeObject(new Message("stop"));
                        } catch (Exception eee) {

                        }
                        Client.this.closeClient(); // в случае исключения тоже харакири
                        this.interrupt();
                        break;
//                    System.out.println("write " + e);

                    }
                } else {
                    break;
                }
            }
        }
    }*/
}
