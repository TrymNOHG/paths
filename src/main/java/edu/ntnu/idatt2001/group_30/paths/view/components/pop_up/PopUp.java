package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;

public class PopUp<T extends Region> extends Dialog<Void> {

    public PopUp(T content, String title) {
        setTitle(title);
        setResizable(true);

        DialogPane dialogPane = new DialogPane();
        setDialogPane(dialogPane);

        dialogPane.getButtonTypes().add(ButtonType.CLOSE);

        dialogPane.setContent(content);
    }

    public void setDialogSize(double width, double height) {
        getDialogPane().setMinSize(width, height);
        getDialogPane().setMaxSize(width, height);
    }
}
