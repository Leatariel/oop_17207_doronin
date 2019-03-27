package ru.nsu.ccfit.doronin.minesweeper.main.textUI;

import ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events.Event;
import ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events.Observer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ViewTextUI {

    ControllerTextUI controller;
    private Event event;        //Событие, которое происходит во вьюшке

    public ViewTextUI(ControllerTextUI controller) {
        this.controller = controller;
        this.event = new Event();
    }

    public void runMainMenu() {
        boolean continueGame = true;
        printStartScreen();
        //Начинаем чтение из входного потока
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (continueGame) {
                String comand = reader.readLine().toLowerCase();
                switch (comand) {
                    case "new":
                        runGame(reader);
                        printStartScreen();
                        break;
                    case "about":
                        System.out.println(controller.getAuthorsMessage());
                        break;
                    case "name":
                        System.out.println("Введите своё имя:");
                        String name = reader.readLine();
                        controller.setNamePlayer(name);
                        break;
                    case "score":
                        //Реализовать score в Model
                        break;
                    case "exit":
                        continueGame = false;
                        break;
                    case "help":
                        printStartScreen();
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("*Ошибка*: " + e.getMessage());
        }

    }

    //Запуск самой игры
    private void runGame(BufferedReader reader) throws Exception {
        boolean continueGame = true;
        String number;
        printGameCommand();

        while (continueGame) {

            printField();

            String comand = reader.readLine().toLowerCase();


            switch (comand) {
                case "open":
                    System.out.println("Какую клетку открыть?");
                    number = reader.readLine();
                    boolean isBomb = !(controller.openCell(number));
                    if(isBomb){
                        continueGame = GameOver();
                        controller.GameOver();
                        reader.readLine();
                    }
                    break;
                case "flag":
                    System.out.println("На какую клетку установить флаг?");
                    number = reader.readLine();
                    controller.setFlag(number);
                    break;
                case "reset":
                    controller.reset();
                    break;
                case "easy":
                    controller.setDifficultEasy();
                    break;
                case "normal":
                    controller.setDifficultNormal();
                    break;
                case "hard":
                    controller.setDifficultHard();
                    break;
                case "help":
                    clearScreen();
                    printGameCommand();
                    break;
                case "exit":
                    continueGame = false;
                    break;


            }
        }
    }

    //Подписать контроллер
    public void subsribeObject(Observer controller){
        event.subscribe(controller);
    }

    public void printAbout(String str){
        System.out.println(str);
    }

    //Конец Игры
    private boolean GameOver(){
        System.out.println("Бомба сделала БУМ!\nВы проиграли!!!\n");
        printField();
        return false;
    }

    //Напечатать главное меню
    private void printStartScreen() {
        System.out.println("Добро Пожаловать в игру сапёр!");
        System.out.println("Введите команду, указанную в скобочках, для активации функций");

        System.out.println("\nНовая игра (new)");
        System.out.println("Об авторе (about)");
        System.out.println("Ввести имя (name)");
        System.out.println("Таблица лидеров (score)");
        System.out.println("Выход (exit)");
        System.out.println("Посмотреть доступные команды (help)");

    }

    //Напечатать команды для навигации во время игры
    private void printGameCommand() {
        System.out.println("Открыть клетку: (open)");
        System.out.println("Установите флажок на клетку (flag)");
        System.out.println("Перезапустить игру с новой картой (reset)");
        System.out.println("Установить уровень сложности. ВНИМАНИЕ, ИГРА ПЕРЕЗАПУСКАЕТСЯ!!!");
        System.out.println("Установить уровень сложности Легко (easy)");
        System.out.println("Установить уровень сложности Легко (normal)");
        System.out.println("Установить уровень сложности Легко (hard)");
        System.out.println("Посмотреть доступные команды (help)");
        System.out.println("Посмотреть доступные команды (exit)");
    }


    //Напечатать поле с клетками
    private void printField(){
        List<String> field = controller.getField();
        field.forEach(n-> System.out.print(" " + n));
    }

    private void clearScreen(){
        for(int i = 0; i < 30; ++i){
            System.out.println();
        }
    }


}
