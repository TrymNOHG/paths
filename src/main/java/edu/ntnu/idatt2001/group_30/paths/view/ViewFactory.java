package edu.ntnu.idatt2001.group_30.paths.view;

import java.lang.reflect.InvocationTargetException;

public class ViewFactory {

    public static <T extends View<?>> T createView(Class<T> viewClass) {
        try {
            return viewClass.getDeclaredConstructor().newInstance();
        } catch (
            InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e
        ) {
            throw new RuntimeException("Failed to create view", e);
        }
    }
}
