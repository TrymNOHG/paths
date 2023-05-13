package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.NewGameController;
import edu.ntnu.idatt2001.group_30.paths.controller.StageManager;
import edu.ntnu.idatt2001.group_30.paths.model.goals.Goal;
import edu.ntnu.idatt2001.group_30.paths.model.goals.GoalFactory;
import edu.ntnu.idatt2001.group_30.paths.model.utils.TextValidation;
import edu.ntnu.idatt2001.group_30.paths.view.components.pop_up.AlertDialog;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

/**
 * This class represents the view for creating/initiating a new game. It, therefore, contains the user
 * interactions for creating, editing, and loading a new game, as well as for creating your player.
 *
 * @author Trym Hamer Gudvangen
 */
public class NewGameView extends View<BorderPane> {

    private final NewGameController newGameController;

    private final TextField playerName;
    private final TextField playerHealth;
    private final TextField playerGold;
    private final ComboBox<String> goalSelector;
    private BorderPane titlePane;
    private VBox buttonVBox;

    /**
     * The constructor of the View class.
     * It creates a new instance of the Pane that the View wraps.
     */
    public NewGameView() {
        super(BorderPane.class);

        newGameController = new NewGameController();

        BorderPane titlePane = createTitlePane();

        playerName = new TextField();
        playerHealth = new TextField();
        playerGold = new TextField();
        goalSelector = new ComboBox<>();

        GridPane playerForm = createPlayerFieldsGridPane();

        VBox playerContainer = createPlayerContainerVBox(playerForm);
        VBox goalContainer = createGoalsDropDownVBox();
        goalContainer.setAlignment(Pos.CENTER);
        VBox mainContainer = createMainContainerVBox(titlePane, playerContainer, goalContainer);

        setupParentPane(mainContainer);

    }

    private GridPane createPlayerFieldsGridPane() {
        playerName.setPromptText("Name");

        playerHealth.setPromptText("Health");
        playerHealth.setTextFormatter(TextValidation.createIntegerTextFormatter());

        playerGold.setPromptText("Gold");
        playerGold.setTextFormatter(TextValidation.createIntegerTextFormatter());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Name:"), 0, 0);
        grid.add(playerName, 1, 0);
        grid.add(new Label("Health:"), 0, 1);
        grid.add(playerHealth, 1, 1);
        grid.add(new Label("Gold:"), 0, 2);
        grid.add(playerGold, 1, 2);

        return grid;
    }

    private VBox createGoalsDropDownVBox() {
        TextField goalsField = new TextField();
        goalSelector.setOnAction((event) -> {
            TextFormatter<Integer> textFormatter = TextValidation.createIntegerTextFormatter();
            switch (goalSelector.getValue()) {
                case "GoldGoal", "HealthGoal", "ScoreGoal" -> goalsField.setTextFormatter(textFormatter);
                case "InventoryGoal" -> goalsField.setTextFormatter(null);
            }

        });

        goalSelector.getItems().addAll("GoldGoal", "HealthGoal", "InventoryGoal", "ScoreGoal");
        Button submit = new Button("Confirm");
        HBox goalsBox = new HBox(new Label("Select a goal:"), goalSelector, goalsField, submit);
        submit.setOnAction(event -> {
            Goal<?> selectedGoal = GoalFactory.getGoal(goalSelector.getValue(), goalsField.getText());
            INSTANCE.addGoal(selectedGoal);
            System.out.println(INSTANCE.getGoals());

            String selectedValue = goalSelector.getValue();
            if (selectedValue != null) {
                goalSelector.getItems().remove(selectedValue);
            }
            goalSelector.getSelectionModel().clearSelection();
            goalsField.clear();
        });


        ObservableList<Goal<?>> goalsList = INSTANCE.getGoals();


        VBox visualGoalsContainer = new VBox();
        visualGoalsContainer.setAlignment(Pos.CENTER);
        for (Goal<?> goal : goalsList) {
            Label goalLabel = new Label(goal.toString());
            visualGoalsContainer.getChildren().add(goalLabel);
        }

        ListChangeListener<Goal<?>> goalsListener = change -> {
            visualGoalsContainer.getChildren().clear();
            for (Goal<?> goal : change.getList()) {
                Label goalLabel = new Label(goal.toString());
                visualGoalsContainer.getChildren().add(goalLabel);
            }
            if (change.getList().size() == 4) {
                goalsBox.getChildren().clear();
            }
        };
        goalsList.addListener(goalsListener);

        return new VBox(10, goalsBox, visualGoalsContainer);
    }


    private VBox createPlayerContainerVBox(GridPane playerForm) {
        playerForm.setAlignment(Pos.CENTER);
        playerForm.setVgap(20);
        VBox playerContainer = new VBox();
        playerContainer.getChildren().addAll(new Label("Create Player"), playerForm);
        playerContainer.setAlignment(Pos.CENTER);
        playerContainer.setSpacing(20);

        return playerContainer;
    }

    private VBox createMainContainerVBox(BorderPane titlePane, VBox playerContainer, VBox goalContainer) {
        VBox mainContainer = new VBox();
        mainContainer.getChildren().addAll(titlePane, playerContainer, goalContainer);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setSpacing(40);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> StageManager.getInstance().goBack());

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            // Start button logic...
        });

        HBox buttonBox = new HBox(10, backButton, startButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox containerWithButtons = new VBox(mainContainer, buttonBox);
        containerWithButtons.setSpacing(20);
        containerWithButtons.setAlignment(Pos.CENTER);

        return containerWithButtons;
    }


    private void setupParentPane(VBox mainContainer) {
        getParentPane().setCenter(mainContainer);
        getParentPane().setStyle("-fx-background-color: #f5f5f5");
        getParentPane().setMaxWidth(Double.MAX_VALUE);
        getParentPane().setMaxHeight(Double.MAX_VALUE);
        getParentPane().setPrefWidth(800);
        getParentPane().setPrefHeight(600);
        getParentPane().setPadding(new Insets(20));
    }

    private BorderPane createTitlePane() {
        BorderPane titlePane = new BorderPane();
        titlePane.setPadding(new Insets(20));
        titlePane.setStyle("-fx-background-color: #f5f5f5");

        Label title = new Label("New Game");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        titlePane.setTop(title);
        BorderPane.setAlignment(title, Pos.TOP_CENTER);

        Button loadButton = new Button("Load");
        Button newButton = new Button("New");

        HBox buttonBox = new HBox(10, loadButton, newButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonVBox = new VBox(buttonBox);
        buttonVBox.setAlignment(Pos.CENTER);
        titlePane.setCenter(buttonVBox);

        loadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Paths files (*.paths)", "*.paths"));

            File resourceDirectory = new File("./src/main/resources/story-files");
            fileChooser.setInitialDirectory(resourceDirectory);

            File selectedFile = fileChooser.showOpenDialog(getParentPane().getScene().getWindow());
            if (selectedFile != null) {
                try {
                    newGameController.setStory(selectedFile);
                    VBox storyVBox = new StoryDisplay.Builder(INSTANCE.getStory())
                            .addStoryName()
                            .addFileInfo(selectedFile)
                            .build();
                    storyVBox.setAlignment(Pos.CENTER);


                    Button pencilButton = createIconButton("/images/pencil.png", 16, 16);
                    Button xButton = createIconButton("/images/remove.png", 16, 16);

                    HBox buttonIcons = new HBox(10, pencilButton, xButton);
                    buttonIcons.setAlignment(Pos.CENTER_LEFT);

                    VBox storyContainer = new VBox(storyVBox, buttonIcons);
                    storyContainer.setAlignment(Pos.CENTER);

                    xButton.setOnAction(event -> {
                        titlePane.getChildren().remove(storyContainer);
                        titlePane.setCenter(buttonBox);
                    });

                    titlePane.setCenter(storyContainer);
                } catch (RuntimeException runtimeException) {
                    AlertDialog.showError(runtimeException.getMessage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return titlePane;
    }

    private Button createIconButton(String imagePath, int width, int height) {
        Button button = new Button();
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            ImageView imageView = new ImageView(imageUrl.toString());
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            button.setGraphic(imageView);
        } else {
            System.err.println("Unable to load image: " + imagePath);
        }
        return button;
    }


}
