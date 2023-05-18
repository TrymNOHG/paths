package edu.ntnu.idatt2001.group_30.paths.view.views;

import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultText;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;

/**
 * A View is a wrapper for a JavaFX Pane.
 * It is responsible for creating the Pane and adding content to it.
 * The View class is generic, and the type parameter T is the type of Pane that the View wraps.
 * The View class can be effortlessly represented into a JavaFX Scene.
 * The View class is also responsible for adding global content to the Scene.
 * @param <T> The type of Pane that the View wraps.
 *
 * @author Nicolai H. Brand.
 */
public class View<T extends Pane> {

    public static final int DEFAULT_WIDTH = 1280;
    public static final int DEFAULT_HEIGHT = 720;
    private final String stylesheet = Objects
        .requireNonNull(getClass().getResource("/stylesheet.css"))
        .toExternalForm();
    private T parentPane;

    /**
     * The constructor of the View class.
     * It creates a new instance of the Pane that the View wraps.
     * @param paneClass The class of the Pane that the View wraps.
     */
    public View(Class<T> paneClass) {
        try {
            parentPane = paneClass.getDeclaredConstructor().newInstance();
            parentPane.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        } catch (
            InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e
        ) {
            //TODO: better error handling
            e.printStackTrace();
        }
    }

    /**
     * Helper method that adds a JavaFX Node to the Pane that the View wraps.
     * @param node The JavaFX Node to be added to the Pane that the View wraps.
     */
    protected void add(Node node) {
        this.parentPane.getChildren().add(node);
    }

    /**
     * Helper method that adds a JavaFX Node to the Pane that the View wraps.
     * @param nodes The JavaFX Nodes to be added to the Pane that the View wraps.
     */
    protected void addAll(Node... nodes) {
        this.parentPane.getChildren().addAll(nodes);
    }

    /**
     * Helper method that adds a JavaFX Node to the Pane that the View wraps.
     * @param nodes The JavaFX Nodes as a List to be added to the Pane that the View wraps.
     */
    protected void addAll(List<Node> nodes) {
        this.parentPane.getChildren().addAll(nodes);
    }

    /**
     * Method that "converts" the View into a JavaFX Scene.
     * It adds global content to the Scene.
     *
     * @return The View as a JavaFX Scene.
     */
    public Scene asScene() {
        //NOTE: the wrapper could also be a HBox or really any other Pane.
        //      The reason for using a BorderPane is that it is easy to add content to the top, bottom, left and right.
        //      A view is free to override this method and use a different Pane.
        BorderPane wrapper = new BorderPane();

        wrapper.setCenter(parentPane);
        wrapper.setBottom(globalFooter());
        wrapper.getStylesheets().add(stylesheet);
        return new Scene(wrapper, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    //NOTE: I am not sure if this is useful
    /**
     * Method that adds global content to the Scene.
     * @return A JavaFX Node that is added to the Scene.
     */
    private Node globalFooter() {
        return DefaultText.small("Made by Trym H. Gudvangen and Nicolai H. Brand");
    }

    /**
     * Getter for the Pane that the View wraps.
     * @return The Pane that the View wraps.
     */
    protected T getParentPane() {
        return parentPane;
    }
}
