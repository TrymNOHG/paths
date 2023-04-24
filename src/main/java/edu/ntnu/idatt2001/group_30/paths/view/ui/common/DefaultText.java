package edu.ntnu.idatt2001.group_30.paths.view.ui.common;

import javafx.scene.text.Text;


/**
 * This class contains the default text used in the application.
 * Used for cohesion between the different texts in the application.
 *
 * @author Nicolai H. Brand.
 */
public class DefaultText {

    /**
     * This method is used to create a small text using project-wide default settings.
     * @param textString The text to be displayed.
     * @return A JavaFX text object.
     */
    public static Text big(String textString) {
        Text text = new Text(textString);
        text.setFont(DefaultFont.big());
        return text;
    }

}
