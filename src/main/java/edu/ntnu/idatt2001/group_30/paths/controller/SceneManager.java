package edu.ntnu.idatt2001.group_30.paths.controller;

import edu.ntnu.idatt2001.group_30.paths.view.AbstractScene;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private final Map<String, Scene> scenes = new HashMap<>();
    private final Stage stage;

    //TODO: Consider implementing a stack of previous scenes
    // this could be used to implement a back button
    //Stack previousScenes = new Stack();

    public SceneManager(Stage stage, AbstractScene startingScene) throws IllegalArgumentException {
        this.stage = stage;
        scenes.put(startingScene.getName(), startingScene.getScene());
        setScene(startingScene.getName());
    }

    void setScene(String name) throws IllegalArgumentException {
        Scene scene = scenes.get(name);
        if (scene == null) {
            throw new IllegalArgumentException("No scene with name " + name + " found.");
        }
        stage.setScene(scene);
    }

    void addScene(AbstractScene scene) {
        scenes.put(scene.getName(), scene.getScene());
    }

    void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    void removeScene(AbstractScene scene) {
        scenes.remove(scene.getName());
    }

    void removeScene(String name) {
        scenes.remove(name);
    }

    public Stage getStage() {
        return stage;
    }
}
