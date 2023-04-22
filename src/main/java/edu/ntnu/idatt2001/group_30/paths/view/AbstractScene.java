package edu.ntnu.idatt2001.group_30.paths.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class AbstractScene {
    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 1000;
    private final Scene scene;
    private final String name;

    public AbstractScene(Parent root, String name) {
        this.scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.name = name;
    }

    public AbstractScene(String name) {
        this.name = name;
        this.scene = new Scene(new Pane(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public AbstractScene(Parent root, String name, int width, int height) {
        this.scene = new Scene(root, width, height);
        this.name = name;
    }

    public void setRoot(Parent root) {
        scene.setRoot(root);
    }

    public void addNode(Node node) {
        scene.getRoot().getChildrenUnmodifiable().add(node);
    }


    public void addStylesheet(String stylesheet) {
        scene.getStylesheets().add(stylesheet);
    }

    public Scene getScene() {
        return scene;
    }

    public String getName() {
        return name;
    }
}
