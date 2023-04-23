package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.View;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//NOTE: Instead of using a String in the hashmap, maybe we should consider using an ENUM.
//      In this case, we don't really need a hashmap at all. The enum value would act as an index into a table
//      of views. This would be faster, and we would not have to worry about misspelling.
/**
 * The class StageManager is responsible for managing the JavaFX Stage of the application.
 * It is implemented as a singleton class and is initialized in the start() method of the Application.
 * It keeps track of all the views (a custom wrapper for a JavaFX Pane that acts as a JavaFX Scene) using a hashmap.
 * It also keeps track of the current view and previous views using a stack.
 * Using a stack makes it easy to go back to the previous view if desirable.
 *
 * @author Nicolai H. Brand.
 */
public class StageManager {
    private final Stage stage;
    private final Map<String, View<?>> views;
    private final Stack<View<?>> viewStack;

    /* static reference to the single instance of the class */
    private static StageManager instance;

    /**
     * private constructor to prevent external instantiation.
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    private StageManager(Stage stage) {
        this.stage = stage;
        this.views = new HashMap<>();
        this.viewStack = new Stack<>();
        stage.show();
    }

    /**
     * Initializes the StageManager.
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @return The StageManager instance.
     */
    public static synchronized StageManager init(Stage stage) {
        if (instance == null) {
            instance = new StageManager(stage);
        }
        return instance;
    }

    /**
     * Returns the StageManager instance if it has been initialized.
     * @return The StageManager instance.
     * @throws IllegalStateException if the StageManager has not been initialized.
     */
    public static synchronized StageManager getInstance() throws IllegalStateException {
        if (instance == null) {
            throw new IllegalStateException("StageManager not initialized.");
        }
        return instance;
    }

    /**
     * Sets the current view to the view with the given name.
     * @param name The name of the view.
     */
    public void setCurrentView(String name) {
        View<?> view = views.get(name);
        if (view == null) {
            throw new IllegalArgumentException("No view with name " + name + " found.");
        }
        pushAndUpdate(view);
    }

    /**
     * Sets the current view of the stage to the previous view in the viewStack.
     */
    public void goBack() {
        if (viewStack.size() > 1) {
            popAndUpdate();
        }
    }

    /**
     * Pushes the given view onto the viewStack and updates the stage.
     * @param view The view to push onto the viewStack.
     */
    private void pushAndUpdate(View<?> view) {
        viewStack.push(view);
        updateView();
    }

    /**
     * Pops the top view from the viewStack and updates the stage.
     */
    private void popAndUpdate() {
        viewStack.pop();
        updateView();
    }

    /**
     * Updates the stage to the current scene of the current view.
     */
    private void updateView() {
        stage.setScene(viewStack.get(viewStack.size() - 1).asScene());
    }

    /**
     * @param view The view to add to the map of viws.
     */
    public void addView(View<?> view) {
        views.put(view.getName(), view);
    }

    /**
     * Variadic method for adding multiple views to the map of views.
     * @param views The views to add to the map of views.
     */
    public void addView(View<?>... views) {
        for (View<?> view : views) {
            this.views.put(view.getName(), view);
        }
    }
}
