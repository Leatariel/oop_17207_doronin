package ru.nsu.ccfit.doronin.lab5.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

class History {

    private LinkedList<String> History = new LinkedList<>();

    //Добавить сообщение в историю
    public void addHistoryEl(String el) {
        // если сообщений больше 10, удаляем первое и добавляем новое
        // иначе просто добавить
        if (History.size() >= 10) {
            History.removeFirst();
            History.add(el);
        } else {
            History.add(el);
        }
    }

    //Получить историю
    public Message getHistoryMessages() {
        Message msg = new Message();
        if (History.size() > 0) {
            try {
                StringBuilder str = new StringBuilder();
                str.append("History messages" + "\n");
                for (String vr : History) {
                    str.append(vr + "\n");
                }
                str.append("/...." + "\n");
                msg.setText(str.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msg;
    }

    public LinkedList<String> getHistory() {
        return History;
    }
}
