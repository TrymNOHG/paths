package edu.ntnu.idatt2001.group_30.paths.view.components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * This class represents a component for creating the player, containing the fields for the player's name,
 * health, and gold, as well as a dropdown box for selecting the player's goal.
 *
 * @author Trym Hamer Gudvangen
 */
public class CreatePlayer extends GridPane {

    private final TextField nameField;
    private final TextField healthField;
    private final TextField goldField;
    private final ComboBox<String> goalBox;

    /**
     * Constructor for the CreatePlayer component.
     */
    public CreatePlayer() {
        setHgap(10);
        setVgap(5);

        nameField = new TextField();
        healthField = new TextField();
        goldField = new TextField();
        goalBox = new ComboBox<>();
        goalBox.getItems().addAll("GoldGoal", "HealthGoal", "InventoryGoal", "ScoreGoal");

        add(new Label("Name:"), 0, 0);
        add(nameField, 1, 0);
        add(new Label("Health:"), 0, 1);
        add(healthField, 1, 1);
        add(new Label("Gold:"), 0, 2);
        add(goldField, 1, 2);
        add(new Label("Goal:"), 0, 3);
        add(goalBox, 1, 3);
    }

    /**
     * Method for getting the name of the player.
     * @return  The name of the player, as a String.
     */
    public String getName() {
        return nameField.getText();
    }

    /**
     * Method for getting the health of the player.
     * @return  The health of the player, as an int.
     */
    public int getHealth() {
        return Integer.parseInt(healthField.getText());
    }

    /**
     * Method for getting the gold of the player.
     * @return  The gold of the player, as an int.
     */
    public int getGold() {
        return Integer.parseInt(goldField.getText());
    }

    /**
     * Method for getting the goal of the player.
     * @return  The goal of the player, as a String.
     */
    public String getGoal() {
        return goalBox.getValue();
    }
}
