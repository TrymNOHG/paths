package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.views.View;
import java.util.Stack;
import javafx.stage.Stage;

/**
 * The class StageManager is responsible for managing the JavaFX Stage of the application.
 * It is implemented as a singleton class and is initialized in the start() method of the Application.
 * It keeps track of the current view and previous views using a stack.
 * Using a stack makes it easy to go back to the previous view if desirable.
 *
 * @author Nicolai H. Brand.
 */
public class StageManager {

    private final Stage stage;
    private final Stack<View<?>> viewStack;

    /* static reference to the single instance of the class */
    private static StageManager instance;

    /**
     * private constructor to prevent external instantiation.
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    private StageManager(Stage stage) {
        this.stage = stage;
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
     * Sets the current view of the stage to the given view.
     * @param view The view to set as the current view.
     */
    public void setCurrentView(View<?> view) {
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
     * Sets the current view of the stage to the previous instance of the given view class.
     * If the view is not in the viewStack, an IllegalArgumentException is thrown.
     * @param viewClass The class of the view to go back to.
     * @throws IllegalArgumentException if the view is not in the viewStack.
     */
    public void goBackTo(Class<? extends View<?>> viewClass) throws IllegalArgumentException {
        while (viewStack.size() > 1 && !viewStack.peek().getClass().equals(viewClass)) {
            popAndUpdate();
        }

        if (viewStack.size() == 1 && !viewStack.peek().getClass().equals(viewClass)) {
            throw new IllegalArgumentException("The view " + viewClass.getSimpleName() + " is not in the viewStack.");
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
     * Returns the primary stage for this application.
     * @return The primary stage for this application.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Returns the name of the current view without 'View' at the end.
     * @return The name of the current view.
     */
    public String getCurrentViewName() {
        return viewStack.peek().getClass().getSimpleName().replace("View", "");
    }

    /**
     * Returns the name of the previous view without 'View' at the end.
     * @return The name of the previous view.
     */
    public String getPreviousViewName() {
        return viewStack.peek().getClass().getSimpleName().replace("View", "");
    }

    /**
     * Returns the entire stack of views.
     * @return The viewStack.
     */
    public Stack<View<?>> getViewStack() {
        return viewStack;
    }
}
