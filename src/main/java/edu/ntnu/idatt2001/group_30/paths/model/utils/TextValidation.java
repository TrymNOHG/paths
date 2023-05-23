package edu.ntnu.idatt2001.group_30.paths.model.utils;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

/**
 * This class represents a text validation.
 *
 * @author Trym Hamer Gudvangen
 */
public class TextValidation {

    /**
     * This method creates a text formatter for integers.
     * @return A text formatter for integers.
     */
    public static TextFormatter<Integer> createIntegerTextFormatter() {
        return createIntegerTextFormatter(100);
    }

    /**
     * This method creates a text formatter for integers.
     * @param startValue   The start value of the text formatter, given as an integer.
     * @return             A text formatter for integers.
     */
    public static TextFormatter<Integer> createIntegerTextFormatter(int startValue) {
        return new TextFormatter<>(
            new IntegerStringConverter(),
            startValue,
            change -> {
                String newText = change.getControlNewText();
                if (newText.matches("-?([1-9][0-9]*)?")) {
                    return change;
                }
                return null;
            }
        );
    }
}
