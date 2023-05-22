package edu.ntnu.idatt2001.group_30.paths.model.utils;

import javafx.scene.control.TextFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextValidationTest {

    @Test
    void createIntegerTextFormatter_should_return_formatter_with_default_start_value() {
        int expectedStartValue = 100;

        TextFormatter<Integer> formatter = TextValidation.createIntegerTextFormatter();

        Assertions.assertEquals(expectedStartValue, formatter.getValue());
    }

    @Test
    void createIntegerTextFormatter_should_return_formatter_with_custom_start_value() {
        int startValue = 50;

        TextFormatter<Integer> formatter = TextValidation.createIntegerTextFormatter(startValue);

        Assertions.assertEquals(startValue, formatter.getValue());
    }
}
