package ru.nsu.ccfit.doronin.minesweeper.main;

import ru.nsu.ccfit.doronin.minesweeper.main.textUI.ControllerTextUI;
import ru.nsu.ccfit.doronin.minesweeper.main.textUI.ViewTextUI;

public class Main {
    public static void main(String[] args) {

//        System.out.println("До запуска таймера");
//        Long time = System.currentTimeMillis();
//
//        Timer t = new Timer();
//        t.schedule(
//                new TimerTask() {
//                    public void run() {
//                        System.out.println("Timer Event!!!");
//                        t.cancel();
//                        t.purge();
//                    }
//                },
//                5000);
//
//        System.out.println("После запуска таймера");

        Model m = new Model();
        ControllerTextUI controller = new ControllerTextUI(m);
        ViewTextUI view = new ViewTextUI(controller);
        view.subsribeObject(controller);
        m.setDifficultNormal();
        view.runMainMenu();
        System.out.println("Завершение main");
    }

    private static void stop(Long t){
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