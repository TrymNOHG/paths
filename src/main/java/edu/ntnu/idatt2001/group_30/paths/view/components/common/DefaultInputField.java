package edu.ntnu.idatt2001.group_30.paths.view.components.common;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * This class contains methods to create different input fields with default layouts.
 *
 * @author Trym Hamer Gudvangen
 */
public class DefaultInputField {

    /**
     * This method creates a text input field with a given label and prompt.
     * @param label     The label of the input field, given as a String.
     * @param prompt    The prompt of the input field, given as a String.
     * @return          An HBox containing the label and the input field.
     */
    public static HBox inputWithLabelAndPrompt(String label, String prompt) {
        Text labelText = new Text(label);
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        return new HBox(labelText, textField);
    }
}
