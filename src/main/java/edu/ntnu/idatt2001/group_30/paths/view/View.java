package edu.ntnu.idatt2001.group_30.paths.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
    public static final int DEFAULT_WIDTH = 900;
    public static final int DEFAULT_HEIGHT = 600;
    private final String stylesheet = "src/main/resources/stylesheet.css";
    private T parentPane;

    /**
     * The constructor of the View class.
     * It creates a new instance of the Pane that the View wraps.
     * @param paneClass The class of the Pane that the View wraps.
     */
    public View(Class<T> paneClass) {
        try {
            parentPane = paneClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
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

    protected void addAll(Node... nodes) {
        this.parentPane.getChildren().addAll(nodes);
    }

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
        //NOTE: the wrapper may not be necessary. I have not found a use for it, although I have a feeling it may be useful :-)
        HBox wrapper = new HBox();
        wrapper.getChildren().add(globalContent());
        wrapper.getChildren().add(parentPane);
        wrapper.getStylesheets().add(stylesheet);
        return new Scene(wrapper, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    //NOTE: I am not sure if this is useful
    /**
     * Method that adds global content to the Scene.
     * @return A JavaFX Node that is added to the Scene.
     */
    private Node globalContent() {
        Text text = new Text("This is global text that is shown on every view.");
        text.setFont(new Font(18));
        text.setX(200);
        text.setY(50);
        return text;
    }

    protected T getParentPane() {
        return parentPane;
    }
}
