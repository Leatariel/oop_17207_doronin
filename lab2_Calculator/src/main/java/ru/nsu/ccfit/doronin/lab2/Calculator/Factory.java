package ru.nsu.ccfit.doronin.lab2.Calculator;

//import ru.nsu.ccfit.doronin.lab2.Calculator.commands.Inject;

import ru.nsu.ccfit.doronin.lab2.Calculator.commands.Inject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

//Фабрика, создающая объекты комманд
public class Factory {

    private static Properties property = null;             //Содержит список комманд
    private static TreeMap<String, Class> classes = null;  //Для хранения классов комманд

    private static Context context;   //Общий контекст

    //Статичный блок
    static {
        //Использовать getResourceAsStream
        try (InputStream reader = new FileInputStream("src/main/java/ru/nsu/ccfit/doronin/lab2/Calculator/commands.properties")) {
//            if (null == reader) {
//                System.out.println("AAAAAAAAA");
//            }

            classes = new TreeMap<String, Class>();

            property = new Properties();
            //Загружаю список комманд из файла
            property.load(reader);

            context = new Context();

            //Напечатать содержимое property
//            property.list(System.out);

        } catch (Exception e) {
            System.out.println("*Ошибка* (Factory) в static блоке: " + e);
        }
    }

    //Создаю команду
    public static CommandClass createCommand(List<String> cmd, Mode mode) throws Exception {
        Class<?> className = null;                                        //Имя класса
        CommandClass commandObj = null;                                   //Объект, экземпляр класса комманды
        String key = property.getProperty(cmd.get(0).toUpperCase());      //Получаю путь к файлу класса

        if (null == key) {
            throw new Exception("Такой команды нет: " + cmd.get(0));
        }

        className = Class.forName(key);

        commandObj = (CommandClass) className.newInstance();

        //Получаю список полей класса и начинаю их обход
        Field[] declaredFields = className.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //Делаю поле доступным
            declaredField.setAccessible(true);
            //Если аннотация класса Inject, то
            if (declaredField.isAnnotationPresent(Inject.class)) {
                //Получаю доступ к аннотации
                Inject annotation = declaredField.getDeclaredAnnotation(Inject.class);
                switch (annotation.arg()) {
                    case CONTEXT:
                        declaredField.set(commandObj, context);
                        break;
                    case ARGS:
                        declaredField.set(commandObj, cmd);
                        break;
                }
            }
        }

        if(mode == Mode.DEBUG){
            CommandClass userProxy = (CommandClass) Proxy.newProxyInstance(
                    commandObj.getClass().getClassLoader(),
                    commandObj.getClass().getInterfaces(),
                    new ProxyCommand(commandObj));

            commandObj = userProxy;
        }


//Так мы делать не хотим, ибо старо (вместо этого юзать аннотации)
//        commandObj.setContext(context);
//        commandObj.setArg();

        return commandObj;
    }
}
