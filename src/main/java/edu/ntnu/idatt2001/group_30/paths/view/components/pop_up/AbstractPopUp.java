package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

/**
 * This class provides a template for creating pop-ups.
 *
 * @author Trym Hamer Gudvangen
 */
public abstract class AbstractPopUp {

    /**
     * This method initializes the pop-up by setting up the UI components and the behavior.
     */
    protected void initialize() {
        setupUiComponents();
        setupBehavior();
    }

    /**
     * This method sets up the UI components of the pop-up.
     */
    protected abstract void setupUiComponents();

    /**
     * This method sets up the behavior of the pop-up.
     */
    protected abstract void setupBehavior();

    /**
     * This method creates the pop-up.
     */
    protected abstract void createPopUp();
}
