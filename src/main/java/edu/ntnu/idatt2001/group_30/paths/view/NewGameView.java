package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.NewGameController;
import edu.ntnu.idatt2001.group_30.paths.view.ui.common.DefaultButton;
import edu.ntnu.idatt2001.group_30.paths.view.ui.common.DefaultText;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * This class represents the view for creating/initiating a new game. It, therefore, contains the user
 * interactions for creating, editing, and loading a new game, as well as for creating your player.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand
 */
public class NewGameView extends View<VBox>{

    private final NewGameController controller;

    /**
     * The constructor of the View class.
     * It creates a new instance of the Pane that the View wraps.
     *
     */
    public NewGameView() {
        super(VBox.class);
        controller = new NewGameController();

        VBox parent = getParentPane();
        parent.setAlignment(Pos.TOP_CENTER);
        parent.setSpacing(20);
        parent.setPadding(new javafx.geometry.Insets(100));

        add(DefaultText.big("Nytt spill"));
        /* Story file chooser */
        add(fileChooserButton());
        add(new Separator());

        /* Player creation */
        add(DefaultText.medium("Opprett spiller"));
        add(DefaultText.small("Navn"));
        TextField playerName = new TextField();
        add(playerName);
        add(DefaultText.small("Helse"));
        TextField playerHealth = new TextField();
        add(playerHealth);
        add(DefaultText.small("Gull"));
        TextField playerGold = new TextField();
        add(playerGold);
        add(new Separator());

        /* Add goals */
        add(DefaultText.medium("Legg til mål"));

        /* Start game */
        add(DefaultButton.big("Start spill", controller.goTo(PlaythroughView.class)));
    }

    public Button fileChooserButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Åpne en paths-fil");

        return DefaultButton.medium("Velg fil", event -> {
            fileChooser.showOpenDialog(controller.getRootStage());
        });
    }



}
