package ru.nsu.ccfit.doronin.minesweeper.main;

import ru.nsu.ccfit.doronin.minesweeper.main.GUI.ControllerGUI;
import ru.nsu.ccfit.doronin.minesweeper.main.GUI.ViewGUI;
import ru.nsu.ccfit.doronin.minesweeper.main.textUI.ControllerTextUI;
import ru.nsu.ccfit.doronin.minesweeper.main.textUI.ViewTextUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        Model model = new Model();


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Добро пожаловать в игру сапёр!");
            System.out.println("Какую версию игры вы хотите запустить?");
            System.out.println("1) TUI\n2) GUI");

            String answer = reader.readLine();

            if (answer.equalsIgnoreCase("1")) {
                ViewTextUI view = new ViewTextUI();
                ControllerTextUI controller = new ControllerTextUI(model, view);
                view.subscribeObject(controller);
                view.runMainMenu();
            } else if (answer.equalsIgnoreCase("2")) {
                ViewGUI view = new ViewGUI();
                ControllerGUI controller = new ControllerGUI(model, view);
                view.subscribe(controller);
                view.setVisible(true);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static void stop(Long t) {
        t = t - System.currentTimeMillis();
    }
}
//
///*
//import java.awt.*;
//import javax.imageio.ImageIO;
//import javax.swing.*;
//
//public class Main {
//    public static void main(String[] args) {
//        JFrame jf = new JFrame();
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setSize(400, 300);
//        jf.setVisible(true);
//
//        // создаем  панель.
//        JPanel p = new JPanel();
//        jf.add(p);
//
//        // к панели добавляем менеджер GridLayout и устанавливаем размеры таблицы 3*3.
//        p.setLayout(new GridLayout(3, 3));
//
//        // к панели добавляем кнопку и устанавливаем для нее менеджер в верхнее расположение.
//
//        p.add(new JButton("start 1"));
//        p.add(new JButton("start 2"));
//        p.add(new JButton("start 3"));
//        p.add(new JButton("start 4"));
//        p.add(new JButton("start 5"));
//        p.add(new JButton("start 6"));
//        p.add(new JButton("start 7"));
//        p.add(new JButton("start 8"));
//        JButton b = new JButton();
//        b.setPreferredSize(new Dimension(50,50));
//        b.setName("999");
//        p.add(b);
////        try {
////            ImageIcon icon = new ImageIcon("src/main/resources/mine.png");
////            p.add(new JButton(icon));
////        } catch (Exception e) {
////            System.out.println(e);
////        }
//    }
//}
//*/
//
//import java.awt.BorderLayout;
//import java.awt.*;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class Main {
//
//    public static void createGUI() {
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        JFrame frame = new JFrame("Test frame");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//
//        JButton northButton = new JButton("North");
//        panel.add(northButton, BorderLayout.NORTH);
//
//        JButton southButton = new JButton("South");
//        panel.add(southButton, BorderLayout.SOUTH);
//
//        JButton eastButton = new JButton("East");
//        panel.add(eastButton, BorderLayout.EAST);
//
//        JButton westButton = new JButton("West");
//        panel.add(westButton, BorderLayout.WEST);
//
//        JButton centerButton = new JButton("Center");
//        panel.add(centerButton, BorderLayout.CENTER);
//
//        frame.getContentPane().add(panel);
//        frame.setPreferredSize(new Dimension(500, 400));
//
//        frame.pack();
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//    }
//
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createGUI();
//            }
//        });
//    }
//}