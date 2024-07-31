package com.ruska112;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionDemo {
    public static int getCountHumans(List<?> objectList) {
        if (objectList == null) {
            throw new IllegalArgumentException();
        }
        return (int) objectList.stream().filter(x -> x instanceof Human).count();
    }

    public static List<String> getListOfPublicMethodsName(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        return new ArrayList<>(Arrays.stream(obj.getClass().getMethods()).filter(x -> Modifier.isPublic(x.getModifiers())).map(Method::getName).toList());
    }

    public static List<String> getListOfSuperclassesNames(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        var result = new ArrayList<String>();
        Class<?> obSuper = obj.getClass().getSuperclass();
        while (obSuper != null) {
            result.add(obSuper.getName());
            obSuper = obSuper.getSuperclass();
        }
        return result;
    }

    public static List<String> getListOfGettersAndSetters(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        var result = new ArrayList<String>();
        for (Method method : obj.getClass().getMethods()) {
            if ((method.getModifiers() == Modifier.PUBLIC &&
                    method.getParameterCount() == 0 &&
                    method.getReturnType() != void.class &&
                    method.getName().startsWith("get")) ||
                    (method.getModifiers() == Modifier.PUBLIC &&
                            method.getParameterCount() == 1 &&
                            method.getReturnType() == void.class &&
                            method.getName().startsWith("set"))) {
                result.add(method.getName());
            }
        }
        return result;
    }
}
