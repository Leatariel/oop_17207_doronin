package ru.nsu.ccfit.doronin.minesweeper.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class ScoreWorker {
    private Map<Long, String> scoreTable;
    private String namePlayer;

    public ScoreWorker() {
        scoreTable = new TreeMap<>();
        namePlayer = "Default";

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/ScoreTable.txt"))) {
            String line;
            while (null != (line = reader.readLine())) {
                int splitter = line.indexOf(",");
                String name = line.substring(0, splitter);
                String score = line.substring(splitter + 1, line.length());

                scoreTable.put(Long.parseLong(score), name);
            }

        } catch (Exception e) {
            System.out.println("Error. ScoreWorker.GetScoreFromFile(): " + e);
        }

    }

    //Записать таблицу в файл
    public void writeScoreTableToFile(){
        try (FileWriter writer = new FileWriter("src/main/resources/ScoreTable.txt", false)) {
            for(Map.Entry t: scoreTable.entrySet()){
                writer.write(t.getValue() + "," + t.getKey() + "\n");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //Дать таблицу с результатами
    public List<String> getScoreTable(){
        List<String> scoreTableList = new ArrayList<>();
        for(Map.Entry elem: scoreTable.entrySet()){
            String str = elem.getValue() + " = " + elem.getKey() + " sec.";
            scoreTableList.add(str);
        }
        return scoreTableList;
    }

    //Добавить новый рекорд в таблицу
    public void addNewScore(Long score){
        scoreTable.put(score, namePlayer);
    }

    //Получить имя игрока
    public String getNamePlayer() {
        return namePlayer;
    }

    //Установить новое имя
    public void setNamePlayer(String newName){
        namePlayer = newName;
    }
}
