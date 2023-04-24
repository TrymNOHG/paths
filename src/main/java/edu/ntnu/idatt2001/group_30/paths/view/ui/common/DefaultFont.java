package edu.ntnu.idatt2001.group_30.paths.view.ui.common;

import javafx.scene.text.Font;

/**
 * This class contains the default font used in the application.
 * Used for cohesion between the different texts in the application.
 *
 * @author Nicolai H. Brand.
 */
public class DefaultFont {

    /*
     * The default font used in the application.
     */
    public static final String DEFAULT_FONT = "Arial";

    /**
     * This method is used to create a big font.
     * @return A JavaFX font.
     */
    public static Font big() {
        return new Font(DEFAULT_FONT, 48);
    }

    /**
     * This method is used to create a medium font.
     * @return A JavaFX font.
     */
    public static Font medium() {
        return new Font(DEFAULT_FONT, 24);
    }

    /**
     * This method is used to create a small font.
     * @return A JavaFX font.
     */
    public static Font small() {
        return new Font(DEFAULT_FONT, 16);
    }
}
