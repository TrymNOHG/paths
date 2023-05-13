package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Region;

public class PopUp<T extends Region, SELF extends PopUp<T, SELF>> extends Dialog<Void> {

    private final DialogPane dialogPane;

    protected PopUp() {
        dialogPane = new DialogPane();
        setDialogPane(dialogPane);
    }

    public static <T extends Region> PopUp<T, ?> create() {
        return new PopUp<>();
    }

    public SELF withTitle(String title) {
        setTitle(title);
        return self();
    }

    public SELF withContent(T content) {
        dialogPane.setContent(content);
        return self();
    }

    public SELF withButton(ButtonType buttonType) {
        dialogPane.getButtonTypes().add(buttonType);
        return self();
    }

    public SELF withDialogSize(double width, double height) {
        getDialogPane().setMinSize(width, height);
        getDialogPane().setMaxSize(width, height);
        return self();
    }

    @SuppressWarnings("unchecked")
    protected SELF self() {
        return (SELF) this;
    }
}
