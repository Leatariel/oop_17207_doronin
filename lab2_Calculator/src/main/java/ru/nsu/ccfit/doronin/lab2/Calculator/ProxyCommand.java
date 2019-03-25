package ru.nsu.ccfit.doronin.lab2.Calculator;

import ru.nsu.ccfit.doronin.lab2.Calculator.commands.Inject;

import org.apache.log4j.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class ProxyCommand implements InvocationHandler {
    private Object obj;
    private static final Logger log = Logger.getLogger(Calculator.class);

    public ProxyCommand(Object f1) {
        obj = f1;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class<?> className = Class.forName(obj.getClass().getName());

        Context c = null;
        List<String> list = null;

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
                        c = (Context) declaredField.get(obj);
                        break;
                    case ARGS:
                        list = (List<String>) declaredField.get(obj);
                        break;
                }
            }
        }


        log.info(obj.getClass().getSimpleName() + ":" + "  Stack Before: " + c.showStack());

        log.info(obj.getClass().getSimpleName() + ":" + "  Values Before: " + c.showVariables());

        log.info(obj.getClass().getSimpleName() + ":" + "  Arguments: " + list);

        Object result = method.invoke(obj, args);

        log.info(obj.getClass().getSimpleName() + ":" + "  Stack after: " + c.showStack());

        log.info(obj.getClass().getSimpleName() + ":" + "  Values After: " + c.showVariables());


        return result;
    }
}
