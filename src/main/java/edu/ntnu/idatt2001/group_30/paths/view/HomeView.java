package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.HomeController;
import edu.ntnu.idatt2001.group_30.paths.view.ui.common.DefaultText;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * The Home page for the application.
 * This is the first page the user sees when starting the application.
 * It contains buttons for starting a new game, showing the settings page and showing the help page.
 *
 * @author Nicolai H. Brand.
 */
public class HomeView extends View<VBox> {

    private final HomeController controller;

    /**
     * The constructor of the class HomeView.
     */
    public HomeView() {
        super(VBox.class);
        controller = new HomeController();

        VBox parent = getParentPane();
        parent.setAlignment(Pos.TOP_CENTER);
        parent.setSpacing(20);
        parent.setPadding(new javafx.geometry.Insets(100));

        add(DefaultText.big("Paths"));
        addAll(getStartMenuButtons());
    }

    /**
     * Creates a button with the given text and action.
     * @param text The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return The created button.
     */
    private Button createButton(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        button.setMinWidth(200);
        button.setMinHeight(100);
        button.setFont(new Font(16));
        button.setAlignment(Pos.CENTER);
        button.setOnAction(action);
        return button;
    }

    /**
     * Creates the buttons for the start menu.
     * @return A list of buttons for the start menu.
     */
    private List<Node> getStartMenuButtons() {
        List<Node> buttons = new ArrayList<>();
        buttons.add(createButton("Nytt spill", controller.goTo(NewGameView.class)));
        buttons.add(createButton("Innstillinger", controller.goTo(NewGameView.class)));
        buttons.add(createButton("Hjelp", controller.goTo(HelpView.class)));
        return buttons;
    }
}
