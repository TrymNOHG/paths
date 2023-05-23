package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.StageManager;
import edu.ntnu.idatt2001.group_30.paths.view.views.HomeView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The class App is the main class of the application Paths.
 * It is responsible for creating the main stage and initializing the stage manager.
 *
 * @author Nicolai H. Brand
 */
public class App extends Application {

    private static StageManager STAGE_MANAGER;

    /**
     * The entry point of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The start method is called by the JavaFX runtime after the init method has returned.
     * Initializes the stage manager.
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.UTILITY);
        stage.setAlwaysOnTop(false);
        stage.setTitle("Paths");
        /* initialize STAGE_MANAGER */
        STAGE_MANAGER = StageManager.init(stage);
        STAGE_MANAGER.setCurrentView(new HomeView());
    }
}
