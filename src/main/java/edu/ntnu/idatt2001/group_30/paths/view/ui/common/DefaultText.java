package edu.ntnu.idatt2001.group_30.paths.view.ui.common;

import javafx.scene.text.Text;


public class DefaultText {

    public static Text big(String textString) {
        Text text = new Text(textString);
        text.setFont(DefaultFont.big());
        return text;
    }

}
