package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.NewGameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * This class represents the view for creating/initiating a new game. It, therefore, contains the user
 * interactions for creating, editing, and loading a new game, as well as for creating your player.
 *
 * @author Trym Hamer Gudvangen
 */
public class NewGameView extends View<BorderPane>{

    private NewGameController newGameController;

    private TextField playerName;
    private TextField playerHealth;
    private TextField playerGold;
    private ComboBox<String> goalSelector;

    /**
     * The constructor of the View class.
     * It creates a new instance of the Pane that the View wraps.
     *
     */
    public NewGameView() {
        super(BorderPane.class);

        newGameController = new NewGameController();

        BorderPane titlePane = createTitlePane();

        playerName = new TextField();
        playerHealth = new TextField();
        playerGold = new TextField();
        goalSelector = new ComboBox<>();

        GridPane playerForm = setPlayerFields();

        VBox playerContainer = new VBox();
        playerContainer.getChildren().addAll(new Label("Create Player"), playerForm);

        VBox goalContainer = setGoalsDropDown();

        VBox mainContainer = new VBox();
        mainContainer.getChildren().addAll(titlePane, playerContainer, goalContainer);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setSpacing(20);

        getParentPane().setCenter(mainContainer);

    }

    private GridPane setPlayerFields() {
        playerName.setPromptText("Name");
        playerHealth.setPromptText("Health");
        playerGold.setPromptText("Gold");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Player"), 0, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(playerName, 1, 1);
        grid.add(new Label("Health:"), 0, 2);
        grid.add(playerHealth, 1, 2);
        grid.add(new Label("Gold:"), 0, 3);
        grid.add(playerGold, 1, 3);

        return grid;
    }

    private VBox setGoalsDropDown() {
        goalSelector.getItems().addAll("GoldGoal", "HealthGoal", "InventoryGoal", "ScoreGoal");
        return new VBox(10, new Label("Select a goal:"), goalSelector);
    }

    private BorderPane createTitlePane() {
        BorderPane titlePane = new BorderPane();
        titlePane.setPadding(new Insets(10));
        titlePane.setStyle("-fx-background-color: white");

        Label title = new Label("New Game");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        titlePane.setTop(title);

        Button loadButton = new Button("Load");
//        loadButton.setOnAction(e -> newGameController.load());

        Button newButton = new Button("New");
//        newButton.setOnAction(e -> newGameController.createNew());

        HBox buttonBox = new HBox(10, loadButton, newButton);
        buttonBox.setAlignment(Pos.CENTER);
        titlePane.setCenter(buttonBox);

        return titlePane;
    }

}
