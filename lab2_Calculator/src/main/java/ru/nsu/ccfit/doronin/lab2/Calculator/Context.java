package ru.nsu.ccfit.doronin.lab2.Calculator;

import org.apache.log4j.*;


import java.util.*;

//Контекст исполнения - объект, содержащий стек и именованные константы
public class Context {

    private Stack<Double> stack = new Stack<>();                  //Стек
    private Map<String, Double> variables = new TreeMap<>();    //Хранилище с именован. конст.

    //Добавить переменную в стек
    public void pushToStack(Double var) {
        stack.push(var);
    }

    //Достать элемент из стека
    public Double popFromStack() throws EmptyStackException {
        return stack.pop();
    }

    //Посмотреть верхушку стека
    public void peekStack() {
        System.out.println(stack.peek());
    }

    //Добавить переменную в список именнованных констант
    public void setNameVar(String key, Double var) {
        variables.put(key, var);
    }

    //Извлечь из списка именнованных констант
    public Double popFromNameVar(String key) throws Exception {
        if (!variables.containsKey(key)) {
            throw new Exception("Такой переменной не существует: " + key);
        }
        return variables.get(key);
    }

    //Показать содержимое стека
    public String showStack(){
//        System.out.println(stack.toString());
        return stack.toString();
    }

    //Показать содержимое массива переменных
    public List<String> showVariables(){
        List<String> l = new ArrayList<>();
        for(Map.Entry<String, Double> entry: variables.entrySet()){
//            System.out.println(entry.getKey() + " = " + entry.getValue());
            l.add(entry.getKey() + " = " + entry.getValue());
        }
        return l;
    }
}
