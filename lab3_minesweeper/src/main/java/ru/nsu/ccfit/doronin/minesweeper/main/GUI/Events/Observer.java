package ru.nsu.ccfit.doronin.minesweeper.main.GUI.Events;

import ru.nsu.ccfit.doronin.minesweeper.main.GUI.ControllerGUI;

public interface Observer {
    //Подписать контроллер
    public void subscribe(ControllerGUI controller);
    //Показать инфу об авторе
    public void showAbout();
    //Показать таблицу скоростей
    public void showScoreTable();

    //Начать новую игру
    public void startNewGame();

    //Установить новое имя пользователя
    public void setNewName(String newName);

    //Установить уровни сложности
//    public void setDifficultEasy();
//    public void setDifficultNormal();
//    public void setDifficultHard();
//
    public void setDifficult(String nameDiff);



    //Открыть клетку
    public void openCell(String index);
    //Установить флаг
    public void setFlag(String index);

}
