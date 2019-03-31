package ru.nsu.ccfit.doronin.minesweeper.main.GUI;

import ru.nsu.ccfit.doronin.minesweeper.main.GUI.Events.Event;
import ru.nsu.ccfit.doronin.minesweeper.main.GUI.Events.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ViewGUI extends JFrame {
    private int arg = 0;

    Observer event;

    JButton cell[];

    private JPanel mainPanel;           //Главная панель
    private JPanel fieldPanel;          //Панель под поле с кнопками/клетками
    private JPanel menuPanel;           //Панель под меню вверху окна
    private JPanel infoPanel;           //Панель для информации
    private JMenu mainMenu;
    private JMenu difficultMenu;
    private JMenu infoMenu;

    private ImageIcon iconMine;
    private ImageIcon iconFlag;

    private ImageIcon iconClose;
    private ImageIcon iconEmpty;

    private ImageIcon iconNumber[];

    private static int countAllCell = 400;
    private int countWorkCell = 100;

    public ViewGUI() throws Exception {


        //Вызываю конструктор класса-родителя и именую окошко
        super("Minesweeper");
        //Устанавливаю границы окна
        setBounds(400, 100, 600, 600);
//        setPreferredSize(new Dimension(500, 500));
        //Размеры окна изменить нельзя
        setResizable(false);
        //Определяю кнопку для зыкрытия окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Создаю главную панель для этого окна
        mainPanel = new JPanel();
        //Устанавливаю её
        setContentPane(mainPanel);
        //Устанавливаю выравнивание
        mainPanel.setLayout(new BorderLayout());

        //Создаю панель с клетками
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(10, 10, 0, 0));

        createIcons();

        createFieldCells();

        createMenus();

        createTextBox();


        //ctrl d - копирует строку
        //ctrl b - перейти к исп
        //shift shift - поиск
        //ctrl shift v - история буффера


    }

    //Создать поле клеток/кнопок
    private void createFieldCells() {
        //Создаю массив кнопок/клеток
        cell = new JButton[countAllCell];
        for (int i = 0; i < countAllCell; ++i) {
            cell[i] = new JButton();
            //Даю кнопке имя. При нажатии смогу идентифицировать, какую кнопку нажал
            cell[i].setName(Integer.toString(i));
//            cell[i].setIcon(iconMine);
            cell[i].setIcon(iconClose);
            //Добавляю слушателя. Реагирует на нажатие кнопки
            cell[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    super.mouseClicked(e);
                    JButton but = (JButton) e.getSource();
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        event.openCell(but.getName());
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        event.setFlag(but.getName());
                    }

                }
            });
            if (i < countWorkCell) {
                fieldPanel.add(cell[i]);
            }
        }
        mainPanel.add(fieldPanel, BorderLayout.CENTER);
    }

    //Создание менюшек и слушателей для них (то бишь, отклик на событие)
    private void createMenus() {
        //Создаю меню для выбора уровня сложности и запуска новой игры
        mainMenu = new JMenu("Действие");
        JMenuItem newGame = new JMenuItem("Новая Игра");
        difficultMenu = new JMenu("Уровень сложности");
        JMenuItem mEasy = new JMenuItem("Легко: 10x10");
        JMenuItem mNormal = new JMenuItem("Нормально: 15x15");
        JMenuItem mHard = new JMenuItem("Сложно: 20x20");
        infoMenu = new JMenu("Информация");
        JMenuItem mAbout = new JMenuItem("О создателе");
        JMenuItem mScore = new JMenuItem("Список лучших");


        mainMenu.add(newGame);
        mainMenu.add(difficultMenu);
        mainMenu.addSeparator();
        difficultMenu.add(mEasy);
        difficultMenu.add(mNormal);
        difficultMenu.add(mHard);
        difficultMenu.addSeparator();
        infoMenu.add(mScore);
        infoMenu.add(mAbout);
        infoMenu.addSeparator();


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mainMenu);
        menuBar.add(infoMenu);
        setJMenuBar(menuBar);

        event = new Event();

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.startNewGame();

            }
        });

        mEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.setDifficult("easy");

            }
        });

        mNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.setDifficult("normal");

            }
        });

        mHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.setDifficult("hard");

            }
        });

        mScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.showScoreTable();
            }
        });

        mAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.showAbout();
            }
        });
    }

    //Создать объект для ввода своего имени
    private void createTextBox() {
        //Задать поле для ввода имени
        JTextField text = new JTextField("Default");

        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.setNewName(text.getText());
            }
        });
        mainPanel.add(text, BorderLayout.SOUTH);
    }

    private void createIcons(){
        iconMine = new ImageIcon("src/main/java/ru/nsu/ccfit/doronin/minesweeper/main/resources/mine.png");
        iconFlag = new ImageIcon("src/main/java/ru/nsu/ccfit/doronin/minesweeper/main/resources/flag.png");

        Image image = iconMine.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH);
        iconMine = new ImageIcon(image);

        image = iconFlag.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH);
        iconFlag = new ImageIcon(image);

        iconClose = new ImageIcon("src/main/java/ru/nsu/ccfit/doronin/" +
                "minesweeper/main/resources/close.png");


        //Создаю иконки чисел
        iconNumber = new ImageIcon[8];
        for (int i = 0; i < 8; ++i) {
            iconNumber[i] = new ImageIcon("src/main/java/ru/nsu/ccfit/doronin/" +
                    "minesweeper/main/resources/" + (i + 1) + ".png");
            image = iconNumber[i].getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH);
            iconNumber[i] = new ImageIcon(image);
        }


        iconEmpty = new ImageIcon("src/main/java/ru/nsu/ccfit/doronin/" +
                "minesweeper/main/resources/empty.png");
    }

    //Подписываем контроллер для наблюдения за ивентами от view
    public void subscribe(ControllerGUI controller) {
        event.subscribe(controller);
    }

    //Создает панель, где выводит инфу о создателе игры
    public void printAbout(String about) {
        JOptionPane.showMessageDialog(null, about, "Информация об авторе игры",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //Показывает панель, на которой написаны лучшие результаты игры
    public void showScoreTable(StringBuilder str) {
        JOptionPane.showMessageDialog(null, str, "Таблица рекордов",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //Вывести новое поле
    //Использую java.util.List<String>, т.к. автоматически выбирает List из awt
    public void showNewField(java.util.List<String> field) {
        for (int i = 0; i < countWorkCell; ++i) {
            String symbol = field.get(i);
            switch (field.get(i)) {
                case "x":
                    cell[i].setIcon(iconClose);
                    break;
                case "F":
                    cell[i].setIcon(iconFlag);
                    break;
                case "*":
                    cell[i].setIcon(iconMine);
                    break;
                case "0":
                    cell[i].setIcon(iconEmpty);
                    break;
                default:
                    cell[i].setIcon(iconNumber[Integer.parseInt(symbol) - 1]);
            }
        }

    }

    //Показать экран проигрыша
    public void showGameOverMessage() {
        JOptionPane.showMessageDialog(null, "Очень жаль, но вы проиграли!!!"
                        + "\nЧтобы начать заново, выберете пункт Новая Игра", "Конец Игры",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //Установить уровень сложности Нормально
    public void setNewDifficult(int newCountCell, int h, int w, String difficult) {
        fieldPanel.removeAll();
        fieldPanel.setLayout(new GridLayout(w, h, 0, 0));
        for (int i = 0; i < newCountCell; i++) {
            fieldPanel.add(cell[i]);
        }
        countWorkCell = newCountCell;
        resizeIconForButton(difficult);
    }

    private void resizeIconForButton(String difficult) {
        iconMine = new ImageIcon("src/main/java/ru/nsu/ccfit/doronin/minesweeper/main/resources/mine.png");
        iconFlag = new ImageIcon("src/main/java/ru/nsu/ccfit/doronin/minesweeper/main/resources/flag.png");
        int h = 0;
        int w = 0;

        if (difficult.equalsIgnoreCase("easy")) {
            h = 50;
            w = 60;
        } else if (difficult.equalsIgnoreCase("normal")) {
            h = 40;
            w = 40;
        } else {
            h = 30;
            w = 30;
        }

        Image image = iconMine.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        iconMine = new ImageIcon(image);


        image = iconFlag.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        iconFlag = new ImageIcon(image);

        for (int i = 0; i < 8; ++i) {
            iconNumber[i] = new ImageIcon("src/main/java/ru/nsu/ccfit/doronin/" +
                    "minesweeper/main/resources/" + (i + 1) + ".png");
            image = iconNumber[i].getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            iconNumber[i] = new ImageIcon(image);
        }
    }

    public void showWinMessage(long time) {
        JOptionPane.showMessageDialog(null, "Поздравляю!!! Вы победили!!!"
                        + "Ваше время: " + time + " сек"
                        + "\nЧтобы начать заново, выберете пункт Новая Игра", "Победа",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
