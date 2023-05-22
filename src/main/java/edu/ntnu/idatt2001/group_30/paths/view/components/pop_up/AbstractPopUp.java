package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

public abstract class AbstractPopUp {

    protected void initialize() {
        setupUiComponents();
        setupBehavior();
    }

    protected abstract void setupUiComponents();

    protected abstract void setupBehavior();

    protected abstract void createPopUp();
}
