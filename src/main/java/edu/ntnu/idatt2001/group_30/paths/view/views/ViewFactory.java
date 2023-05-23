package edu.ntnu.idatt2001.group_30.paths.view.views;

import java.lang.reflect.InvocationTargetException;

/**
 * A factory for creating views.
 */
public class ViewFactory {

    /**
     * Given a class of a view, this method creates an instance of that view.
     * @param viewClass The class of the view to be created.
     * @return          An instance of the view.
     * @param <T>       The type of the view.
     */
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
