package edu.ntnu.idatt2001.group_30.paths.view.ui.common;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * This class is used to create buttons with default settings.
 * Used for cohesion between the different buttons in the application.
 *
 * @author Nicolai H. Brand.
 *
 */
public class DefaultButton {

    /**
     * This method is used to create a big button with project-wide default settings.
     * @param textString The text to be displayed on the button.
     * @return A JavaFX button.
     */
    public static Button big(String textString) {
        Button button = new Button(textString);
        button.setAlignment(Pos.CENTER);
        button.setMinWidth(200);
        button.setMinHeight(100);
        button.setFont(DefaultFont.medium());
        return button;
    }

    /**
     * This method is used to create a big button with project-wide default settings.
     * @param textString The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A JavaFX button.
     */
    public static Button big(String textString, EventHandler<ActionEvent> action) {
        Button button = big(textString);
        button.setOnAction(action);
        return button;
    }

    /**
     * This method is used to create a medium button with project-wide default settings.
     * @param textString The text to be displayed on the button.
     * @return A JavaFX button.
     */
    public static Button medium(String textString) {
        Button button = new Button(textString);
        button.setAlignment(Pos.CENTER);
        button.setFont(DefaultFont.small());
        return button;
    }

    /**
     * This method is used to create a medium button with project-wide default settings.
     * @param textString The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A JavaFX button.
     */
    public static Button medium(String textString, EventHandler<ActionEvent> action) {
        Button button = medium(textString);
        button.setOnAction(action);
        return button;
    }

    /**
     * This method is used to create a small button with project-wide default settings.
     * @param textString The text to be displayed on the button.
     * @return A JavaFX button.
     */
    public static Button small(String textString) {
        Button button = new Button(textString);
        button.setAlignment(Pos.CENTER);
        button.setFont(DefaultFont.small());
        return button;
    }

    /**
     * This method is used to create a small button with project-wide default settings.
     * @param textString The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A JavaFX button.
     */
    public static Button small(String textString, EventHandler<ActionEvent> action) {
        Button button = small(textString);
        button.setOnAction(action);
        return button;
    }
}
