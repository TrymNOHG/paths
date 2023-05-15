package edu.ntnu.idatt2001.group_30.paths.model.utils;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class TextValidation {

    public static TextFormatter<Integer> createIntegerTextFormatter() {
        return createIntegerTextFormatter(100);
    }

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
