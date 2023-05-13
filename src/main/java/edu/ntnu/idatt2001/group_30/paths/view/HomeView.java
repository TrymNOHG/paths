package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.HomeController;
import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultButton;
import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultText;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

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
     * Creates the buttons for the start menu.
     * @return A list of buttons for the start menu.
     */
    private List<Button> getStartMenuButtons() {
        List<Button> buttons = new ArrayList<>();
        buttons.add(createButton("Nytt spill", controller.goTo(CreatePlayerView.class)));
        buttons.add(createButton("Innstillinger", controller.goTo(NewGameView.class)));
        buttons.add(createButton("Hjelp", controller.goTo(HelpView.class)));
        return buttons;
    }
}
