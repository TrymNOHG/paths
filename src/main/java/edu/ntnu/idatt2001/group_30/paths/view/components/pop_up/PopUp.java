package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import javafx.scene.control.*;
import javafx.scene.layout.Region;

/**
 * This class provides a template for creating pop-ups.
 * @param <T>       The type of the content of the pop-up.
 * @param <SELF>    The type of the pop-up.
 */
public class PopUp<T extends Region, SELF extends PopUp<T, SELF>> extends Dialog<Void> {

    private final DialogPane dialogPane;

    /**
     * This constructor sets up the dialog pane.
     */
    protected PopUp() {
        dialogPane = new DialogPane();
        setDialogPane(dialogPane);
    }

    /**
     * This method creates a pop-up.
     * @return      The pop-up.
     * @param <T>   The type of the content of the pop-up.
     */
    public static <T extends Region> PopUp<T, ?> create() {
        return new PopUp<>();
    }

    public SELF withTitle(String title) {
        setTitle(title);
        return self();
    }

    /**
     * This method returns the dialog pane.
     * @param content   The content of the dialog pane.
     * @return          The dialog pane.
     */
    public SELF withContent(T content) {
        dialogPane.setContent(content);
        return self();
    }

    /**
     * This method returns the dialog pane.
     * @param buttonType    The button type of the dialog pane.
     * @return              The dialog pane.
     */
    public SELF withButton(ButtonType buttonType) {
        dialogPane.getButtonTypes().add(buttonType);
        return self();
    }

    /**
     * This method returns the dialog pane.
     * @return    The dialog pane.
     */
    public SELF withoutCloseButton() {
        // Add a close button type to the dialog
        ButtonType closeButtonType = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogPane.getButtonTypes().add(closeButtonType);

        // Get the actual button and make it invisible and unmanaged
        Button closeButton = (Button) dialogPane.lookupButton(closeButtonType);
        closeButton.setVisible(false);
        closeButton.setManaged(false);

        return self();
    }

    /**
     * This method returns the dialog pane.
     * @param width     The width of the dialog pane.
     * @param height    The height of the dialog pane.
     * @return          The dialog pane.
     */
    public SELF withDialogSize(double width, double height) {
        getDialogPane().setMinSize(width, height);
        getDialogPane().setMaxSize(width, height);
        return self();
    }

    /**
     * This method returns the dialog pane.
     * @return  The dialog pane.
     */
    @SuppressWarnings("unchecked")
    protected SELF self() {
        return (SELF) this;
    }
}
