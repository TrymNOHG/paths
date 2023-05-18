package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.views.HomeView;
import edu.ntnu.idatt2001.group_30.paths.view.views.View;
import edu.ntnu.idatt2001.group_30.paths.view.views.ViewFactory;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * The class Controller is the superclass of all controllers.
 * A Controller is responsible for managing one specific view.
 * Every controller has a reference to the StageManager singleton.
 *
 * @author Nicolai H. Brand, Trym Hamer Gudvangen
 */
public class Controller {

    protected final StageManager STAGE_MANAGER = StageManager.getInstance();
    protected final Map<Class<? extends View<?>>, Runnable> availableViews = new HashMap<>();

    @SafeVarargs
    public Controller(Class<? extends View<?>>... viewClasses) {
        for (Class<? extends View<?>> viewClass : viewClasses) {
            availableViews.put(viewClass, () -> STAGE_MANAGER.setCurrentView(ViewFactory.createView(viewClass)));
        }
    }

    /**
     * Returns an EventHandler that will set the current view to the view of the given class.
     * @param viewClass The class of the view to set as the current view.
     * @return An EventHandler that will set the current view to the view of the given class.
     */
    public EventHandler<ActionEvent> goTo(Class<? extends View<?>> viewClass) {
        return actionEvent -> availableViews.get(viewClass).run();
    }

    /**
     * @return An EventHandler that will set the current view to the previous view.
     */
    public EventHandler<ActionEvent> goBack() {
        return actionEvent -> STAGE_MANAGER.goBack();
    }

    /**
     * Go back to the previous instance of the given view class.
     * @param viewClass The class of the view to go back to.
     * @return An EventHandler that will set the current view to the previous instance of the given view class.
     */
    public EventHandler<ActionEvent> goBackTo(Class<? extends View<?>> viewClass) {
        return actionEvent -> STAGE_MANAGER.goBackTo(viewClass);
    }

    public Stage getRootStage() {
        return STAGE_MANAGER.getStage();
    }

    public void goToHome() {
        STAGE_MANAGER.setCurrentView(ViewFactory.createView(HomeView.class));
    }
}
