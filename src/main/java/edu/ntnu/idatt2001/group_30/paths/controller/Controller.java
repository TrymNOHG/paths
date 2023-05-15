package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.View;
import edu.ntnu.idatt2001.group_30.paths.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

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
        for(Class<? extends View<?>> viewClass : viewClasses) {
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

    public Stage getRootStage() {
        return STAGE_MANAGER.getStage();
    }
}
