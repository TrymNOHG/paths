package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.View;
import edu.ntnu.idatt2001.group_30.paths.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

    public EventHandler<ActionEvent> goTo(Class<? extends View<?>> viewClass) {
        return actionEvent -> availableViews.get(viewClass).run();
    }
}

