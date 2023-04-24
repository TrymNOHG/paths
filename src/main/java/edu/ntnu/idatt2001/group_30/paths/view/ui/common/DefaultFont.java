package edu.ntnu.idatt2001.group_30.paths.view.ui.common;

import javafx.scene.text.Font;

public class DefaultFont {

    public static final String DEFAULT_FONT = "Arial";

    public static Font big() {
        return new Font(DEFAULT_FONT, 48);
    }

    public static Font medium() {
        return new Font(DEFAULT_FONT, 24);
    }

    public static Font small() {
        return new Font(DEFAULT_FONT, 16);
    }
}
