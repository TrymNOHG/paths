package edu.ntnu.idatt2001.group_30.paths.view.components.common;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * This class is used to create Text objects that are bound to an observable property.
 * This means that the text will update when the property changes.
 * This is used to create a dynamic UI.
 *
 * @author Nicolai H. Brand.
 */
public class Ref {

    /**
     * Creates a Text object that is bound to the given observable property.
     * @param ref The observable property to bind the text to.
     * @return A Text object that is bound to the given observable property.
     */
    public static Text bigText(ObservableValue<? extends String> ref) {
        Text text = new Text();
        text.textProperty().bind(ref);
        text.setFont(DefaultFont.big());
        return text;
    }

    /**
     * Creates a Text object that is bound to the given observable property.
     * @param ref The observable property to bind the text to.
     * @return A Text object that is bound to the given observable property.
     */
    public static Text mediumText(ObservableValue<? extends String> ref) {
        Text text = new Text();
        text.textProperty().bind(ref);
        text.setFont(DefaultFont.medium());
        return text;
    }

    /**
     * Creates a Text object that is bound to the given observable property.
     * @param ref The observable property to bind the text to.
     * @return A Text object that is bound to the given observable property.
     */
    public static Text smallText(ObservableValue<? extends String> ref) {
        Text text = new Text();
        text.textProperty().bind(ref);
        text.setFont(DefaultFont.small());
        return text;
    }

    /**
     * Creates a TextArea object that is bound to the given observable property.
     * @param ref The observable property to bind the text to.
     * @return A TextArea object that is bound to the given observable property.
     */
    public static TextArea smallTextArea(ObservableValue<? extends String> ref) {
        TextArea text = new TextArea();
        text.textProperty().bind(ref);
        text.setFont(DefaultFont.small());
        return text;
    }
}
