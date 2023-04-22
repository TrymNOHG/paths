package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.SceneManager;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.stage.Stage;

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
    private static SceneManager sceneManager;

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
    public void start(Stage stage) {
        HomeScene homeScene = new HomeScene();
        sceneManager = new SceneManager(stage, homeScene);
        App.stage = sceneManager.getStage();
        App.stage.show();
    }

    public static void setRoot(Pane pane) {
        stage.getScene().setRoot(pane);
    }
}
