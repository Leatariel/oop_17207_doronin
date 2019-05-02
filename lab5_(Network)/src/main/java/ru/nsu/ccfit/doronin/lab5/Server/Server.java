package ru.nsu.ccfit.doronin.lab5.Server;


import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Properties;

public class Server {

    //Список всех потоков для работы с клиентами-сокетами
    public static LinkedList<SocketListener> serverList = new LinkedList<>();
    //История переписки
    public static History history;
    //Если true, то логируем все действия на стороне сервера
    public static boolean IS_LOG = false;

    private static final Logger log = Logger.getLogger(Server.class);


    public static void main(String[] args) {

        try (InputStream inStr = Server.class.getClassLoader().getResourceAsStream("ServerSettings.properties")) {
            //Читаем настройки из конфигурационного файла (порт, нужно ли логировать)
            Properties property = new Properties();
            property.load(inStr);

            String portStr = property.getProperty("port");
            String isLogStr = property.getProperty("log");

            if (portStr == null || isLogStr == null) {
                throw new Exception("Конфигурационный файл задан неверно!!!");
            }

            int port = Integer.parseInt(portStr);
            IS_LOG = Boolean.parseBoolean(isLogStr);

            history = new History();

            try (ServerSocket server = new ServerSocket(port)) {
                if(IS_LOG){
                    log.info("-------------------------------------");
                    log.info("Server start work");
                    log.info("Access port: " + port);
                }
                while (true) {
                    // Ждём нового соединения
                    Socket socket = server.accept();
                    // Добавляем новое соединенние в список
                    serverList.add(new SocketListener(socket));
                }
            } catch (Exception ee) {
                System.out.println(ee);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void closeServer(){
        System.exit(0);
    }
}