package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.HomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * The Home page for the application.
 * This is the first page the user sees when starting the application.
 * It contains buttons for starting a new game, showing the settings page and showing the help page.
 *
 * @author Nicolai H. Brand.
 */
public class HomeView extends View<BorderPane> {

    private final HomeController controller;

    /**
     * The constructor of the class HomeView.
     */
    public HomeView() {
        super("Home", BorderPane.class);
        controller = new HomeController();
        Text text = new Text("Paths");
        text.setFont(new Font(36));

        VBox box = new VBox(30);
        box.setAlignment(Pos.TOP_LEFT);
        box.setSpacing(20);
        box.setPadding(new javafx.geometry.Insets(100));
        box.getChildren().add(text);

        box.getChildren().addAll(getStartMenuButtons());

        add(box);
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
    private List<Button> getStartMenuButtons() {
        List<Button> buttons = new ArrayList<>();
        buttons.add(createButton("Nytt spill", controller::startNewGame));
        buttons.add(createButton("Innstillinger", controller::showSettings));
        buttons.add(createButton("Hjelp", controller::showHelp));
        return buttons;
    }
}
