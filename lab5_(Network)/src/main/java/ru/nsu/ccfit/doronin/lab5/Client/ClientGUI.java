package ru.nsu.ccfit.doronin.lab5.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientGUI extends JFrame {

    private Controller controller;

    private JPanel mainPanel;           //Главная панель
    private JTextArea printScreen;
    private JTextArea writeScreen;

    private JScrollPane scrollPrint;
    private JScrollPane scrollWrite;

    private KeyListener keyListener;

    public ClientGUI(Controller contr) throws HeadlessException {
        //Вызываю конструктор класса-родителя и именую окошко
        super("Client");
        //Устанавливаю границы окна
        setBounds(400, 100, 300, 500);
        //Размеры окна изменить нельзя
        setResizable(false);
        //Определяю кнопку для зыкрытия окна
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Создаю главную панель для этого окна
        mainPanel = new JPanel();
        //Устанавливаю её
        setContentPane(mainPanel);
        //Устанавливаю выравнивание
        mainPanel.setLayout(new BorderLayout());

        printScreen = new JTextArea(15, 10);
        writeScreen = new JTextArea(9, 5);


        //Запрещает ввод с клавиатуры, только вывод на экран
        printScreen.setEditable(false);

        writeScreen.setLineWrap(true);
        writeScreen.setWrapStyleWord(true);

        printScreen.setLineWrap(true);
        printScreen.setWrapStyleWord(true);

        writeScreen.setFont(writeScreen.getFont().deriveFont(15f));
        printScreen.setFont(printScreen.getFont().deriveFont(15f));

        printScreen.setText("Enter your nickname:\n");

        scrollPrint = new JScrollPane(printScreen);
        scrollWrite = new JScrollPane(writeScreen);

//        scrollPrint.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
//            public void adjustmentValueChanged(AdjustmentEvent e) {
//                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//            }});
//
//        scrollWrite.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
//            public void adjustmentValueChanged(AdjustmentEvent e) {
//                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//            }});

        mainPanel.add(scrollPrint, BorderLayout.NORTH);
        mainPanel.add(scrollWrite, BorderLayout.SOUTH);

        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                didAction(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            private void didAction(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controller.sendMessage(writeScreen.getText());
                    writeScreen.setText(null);

                }
            }
        };

        writeScreen.addKeyListener(keyListener);

        controller = contr;

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    controller.closeClient();
                }
            }
        });
        ;
    }

    public void closeGUI() {
        System.exit(0);
    }

    public void printMessageOnScreen(String message) {
        printScreen.append(message);
    }

    public void setNickname(String name) {
        this.setTitle(name);
    }
}
