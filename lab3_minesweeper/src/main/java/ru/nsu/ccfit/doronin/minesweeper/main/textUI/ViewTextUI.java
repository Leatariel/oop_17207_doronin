package ru.nsu.ccfit.doronin.minesweeper.main.textUI;

import ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events.Event;
import ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events.Observer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class ViewTextUI {

    private Event event;                //Событие, которое происходит во вьюшке
    private boolean continueGame;       //Отвечает за продолдение игры

    public ViewTextUI() {
        this.event = new Event();
        continueGame = true;
    }

    public void runMainMenu() {
        printStartScreen();
        //Начинаем чтение из входного потока
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (continueGame) {
                String comand = reader.readLine().toLowerCase();
                switch (comand) {
                    case "new":
                        event.update(comand);
                        runGame(reader);
                        printStartScreen();
                        continueGame = true;
                        break;
                    case "about":
                        event.update(comand);
//                        System.out.println(controller.getAuthorsMessage());
                        break;
                    case "name":
                        System.out.println("Введите своё имя:");
                        String name = reader.readLine();
                        event.update(comand, name);
//                        controller.setNamePlayer(name);
                        break;
                    case "score":
                        //Реализовать score в Model
                        event.update(comand);
                        break;
                    case "exit":
                        continueGame = false;
                        event.update("closeGame");
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

        String number;
        printGameCommand();

        while (continueGame) {

//            printField();
            event.update("printField");

            String comand = reader.readLine().toLowerCase();


            switch (comand) {
                case "open":
                    System.out.println("Какую клетку открыть?");
                    number = reader.readLine();
                    event.update(comand, number);
//                    boolean isBomb = !(controller.openCell(number));
//                    if(isBomb){
//                        continueGame = GameOver();
//                        controller.GameOver();
//                        reader.readLine();
//                    }
                    break;
                case "flag":
                    System.out.println("На какую клетку установить флаг?");
                    number = reader.readLine();
                    event.update(comand, number);
//                    controller.setFlag(number);
                    break;
                case "reset":
                    event.update(comand);
//                    controller.reset();
                    break;
                case "easy":
                    event.update(comand);
//                    controller.setDifficultEasy();
                    break;
                case "normal":
                    event.update(comand);
//                    controller.setDifficultNormal();
                    break;
                case "hard":
                    event.update(comand);
//                    controller.setDifficultHard();
                    break;
                case "help":
                    clearScreen();
                    printGameCommand();
                    break;
                case "exit":
                    continueGame = false;
                    event.update(comand);
                    break;
            }
        }
    }

    //Подписать контроллер
    public void subscribeObject(ControllerTextUI controller){
        event.subscribe(controller);
    }

    //Напечатать информацию об авторах
    public void printAbout(String str){
        System.out.println(str);
    }

    //Конец Игры
    public void gameOver(){
        System.out.println("Бомба сделала БУМ!\nВы проиграли!!!\n");
        continueGame = false;
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

    //Напечатать таблицу с результатами
    public void printScoreTable(List<String> scoreTable){
        scoreTable.forEach(n -> System.out.println(n));
    }

    //Напечатать поле с клетками
    public void printField(List<String> field){
        field.forEach(n-> System.out.print(" " + n));
    }

    private void clearScreen(){
        for(int i = 0; i < 30; ++i){
            System.out.println();
        }
    }

    //Показать сообщение о победе
    public void showWinMessage(Long time){
        System.out.println("Поздравляем вас!!! Вы победили!!!\nВаше время: " + time);
        continueGame = false;
    }

}
