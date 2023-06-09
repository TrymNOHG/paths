package edu.ntnu.idatt2001.group_30.paths.view.views;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.controller.NewGameController;
import edu.ntnu.idatt2001.group_30.paths.controller.StageManager;
import edu.ntnu.idatt2001.group_30.paths.view.components.StoryDisplay;
import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultButton;
import edu.ntnu.idatt2001.group_30.paths.view.components.pop_up.AlertDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * This class represents the view for creating/initiating a new game. It, therefore, contains the user
 * interactions for creating, editing, and loading a new game, as well as for creating your player.
 *
 * @author Trym Hamer Gudvangen
 */
public class LoadGameView extends View<BorderPane> {

    private final NewGameController newGameController;

    private BorderPane titlePane;
    private VBox buttonVBox;
    private Button startButton;
    private Button loadButton;
    private Button newButton;
    private HBox buttonBox;

    /**
     * The constructor of the View class.
     * It creates a new instance of the Pane that the View wraps.
     */
    public LoadGameView() {
        super(BorderPane.class);
        newGameController = new NewGameController();

        BorderPane titlePane = createTitlePane();

        VBox mainContainer = createMainContainerVBox(titlePane);

        if (INSTANCE.getStory() != null) {
            try {
                addStoryPane();
            } catch (IOException e) {
                AlertDialog.showError(e.getMessage());
            }
        }

        setupParentPane(mainContainer);
    }

    /**
     * Adds the story pane to the view.
     * @param titlePane The title pane of the view.
     * @return          The main container of the view.
     */
    private VBox createMainContainerVBox(BorderPane titlePane) {
        VBox mainContainer = new VBox();
        mainContainer.getChildren().addAll(titlePane);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setSpacing(100);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> StageManager.getInstance().goBack());
        getParentPane().setBottom(backButton);

        startButton = DefaultButton.medium("Start game", newGameController.goTo(PlaythroughView.class));
        startButton.setVisible(false);

        VBox containerWithButtons = new VBox(mainContainer, startButton);
        containerWithButtons.setSpacing(20);
        containerWithButtons.setAlignment(Pos.CENTER);

        return containerWithButtons;
    }

    /**
     * Sets up the parent pane of the view.
     * @param mainContainer The main container of the view.
     */
    private void setupParentPane(VBox mainContainer) {
        getParentPane().setCenter(mainContainer);
        getParentPane().setStyle("-fx-background-color: #f5f5f5");
        getParentPane().setMaxWidth(Double.MAX_VALUE);
        getParentPane().setMaxHeight(Double.MAX_VALUE);
        getParentPane().setPrefWidth(800);
        getParentPane().setPrefHeight(600);
        getParentPane().setPadding(new Insets(20));
    }

    /**
     * Adds the story pane to the view.
     * @return  The story pane.
     */
    private BorderPane createTitlePane() {
        BorderPane titlePane = new BorderPane();
        titlePane.setPadding(new Insets(20));
        titlePane.setStyle("-fx-background-color: #f5f5f5");

        Label title = new Label("New Game");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        titlePane.setTop(title);
        BorderPane.setAlignment(title, Pos.TOP_CENTER);
        titlePane.getTop().setTranslateY(-70);

        loadButton = new Button("Load");
        newButton = new Button("New");

        buttonBox = new HBox(10, loadButton, newButton);
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
                    addStoryPane();
                } catch (RuntimeException runtimeException) {
                    AlertDialog.showError(runtimeException.getMessage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        newButton.setOnAction(newGameController.goTo(NewStoryView.class));

        this.titlePane = titlePane;
        return titlePane;
    }

    /**
     * Creates an icon button.
     * @param imagePath The path to the image.
     * @param width     The width of the image.
     * @param height    The height of the image.
     * @return          The button.
     */
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

    /**
     * Adds the story pane to the view.
     * @throws IOException  If the story pane cannot be added.
     */
    private void addStoryPane() throws IOException {
        VBox storyVBox = new StoryDisplay.Builder(INSTANCE.getStory())
            .addStoryName()
            .addFileInfo(INSTANCE.getStoryFile())
            .build();
        storyVBox.setAlignment(Pos.CENTER);

        Button pencilButton = createIconButton("/images/pencil.png", 16, 16);
        Button xButton = createIconButton("/images/remove.png", 16, 16);

        HBox buttonIcons = new HBox(10, pencilButton, xButton);
        buttonIcons.setAlignment(Pos.CENTER);

        VBox storyContainer = new VBox(storyVBox, buttonIcons);
        storyContainer.setAlignment(Pos.CENTER);

        pencilButton.setOnAction(newGameController.goTo(NewStoryView.class));

        xButton.setOnAction(event -> {
            titlePane.getChildren().remove(storyContainer);
            titlePane.setCenter(buttonBox);
            startButton.setVisible(false);

            INSTANCE.setStory(null);
        });

        titlePane.setCenter(storyContainer);
        startButton.setVisible(true);
    }
}
