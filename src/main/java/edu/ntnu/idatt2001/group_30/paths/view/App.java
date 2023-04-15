package edu.ntnu.idatt2001.group_30.paths.view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The class App is the main class of the application Paths.
 * It is responsible for creating the main stage and scene.
 *
 * @author Nicolai H. Brand
 */
public class App extends Application {
    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 1000;
    private static Stage stage;

    /**
     * The entry point of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The start method is called by the JavaFX runtime after the init method has returned.
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) throws IOException, InstantiationException {
        App.stage = stage;
        App.stage.setTitle("Paths");
        AnchorPane anchorPane = new AnchorPane();

        VBox storyVBox = new StoryDisplay.Builder("Bones")
                .addTimeSavedInfo()
                .addFileLocationInfo()
                .addFileNameInfo()
                .build();

        Pane pane = new Pane();
        pane.setStyle("-fx-border-color: black");
        pane.getChildren().setAll(storyVBox);


        anchorPane.getChildren().add(pane);

        Scene scene = new Scene(anchorPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        App.stage.setScene(scene);
        App.stage.show();


        AlertDialog.showInformation("Welcome to our application", "Info test");
        if(!AlertDialog.showConfirmation("Are you ready to get started?", "Confirmation test")) {
            AlertDialog.showError("Error message would go here");
            System.exit(0);
        }

    }
}
