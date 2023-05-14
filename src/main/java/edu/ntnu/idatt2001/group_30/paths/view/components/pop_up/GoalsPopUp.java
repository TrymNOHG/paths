package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import edu.ntnu.idatt2001.group_30.paths.model.goals.GoalFactory;
import edu.ntnu.idatt2001.group_30.paths.model.goals.GoalType;
import edu.ntnu.idatt2001.group_30.paths.model.utils.TextValidation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

public class GoalsPopUp {

    private TextField healthField;
    private TextField goldField;
    private TextField scoreField;
    private Button saveButton;

    public GoalsPopUp() {
        healthField = new TextField();
        healthField.setTextFormatter(TextValidation.createIntegerTextFormatter(INSTANCE.getHealthGoal() == null ?
                100 : INSTANCE.getHealthGoal().getGoalValue()));
        healthField.setPromptText("Add health goal");

        goldField = new TextField();
        goldField.setTextFormatter(TextValidation.createIntegerTextFormatter(INSTANCE.getGoldGoal() == null ?
                        100 : INSTANCE.getGoldGoal().getGoalValue()));
        goldField.setPromptText("Add gold goal");

        scoreField = new TextField();
        scoreField.setTextFormatter(TextValidation.createIntegerTextFormatter(INSTANCE.getScoreGoal() == null ?
                100 : INSTANCE.getScoreGoal().getGoalValue()));
        scoreField.setPromptText("Add score goal");

        saveButton = new Button("Save");

        TextField inventoryField = new TextField();
        inventoryField.setPromptText("Add inventory item");

        Button addButton = new Button("+");

        //TODO: connect this list to the list of an inventory goal
        // Connect other goals to INSTANCE
        // Connect stats to INSTANCE and to Player
        //

        ObservableList<String> items = FXCollections.observableArrayList();
        if(INSTANCE.getInventoryGoal() != null) {
            items.addAll(INSTANCE.getInventoryGoal().getGoalValue());
        }
        TableView<String> inventoryTable = new TableView<>(items);
        TableColumn<String, String> itemColumn = new TableColumn<>("Items");
        itemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        inventoryTable.getColumns().add(itemColumn);
        itemColumn.prefWidthProperty().bind(inventoryTable.widthProperty());
        inventoryTable.setMaxHeight(200);


        Button deleteButton = new Button();
        URL imageUrl = getClass().getResource("/images/pencil.png");
        if(imageUrl != null) {
            ImageView trashIcon = new ImageView(new Image(imageUrl.toString()));
            trashIcon.setFitHeight(25);
            trashIcon.setFitWidth(25);
            deleteButton.setGraphic(trashIcon);
        } else {
            System.err.println("Something is wrong with the trash image resource link");
        }


        addButton.setOnAction(e -> {
            if (!inventoryField.getText().isBlank()) {
                items.add(inventoryField.getText());
                inventoryField.clear();
            }
        });

        deleteButton.setOnAction(e -> {
            String selectedItem = inventoryTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                items.remove(selectedItem);
            }
        });

        VBox content = new VBox(
                new Label("Health:"), healthField,
                new Label("Gold:"), goldField,
                new Label("Score:"), scoreField,
                new Label("Inventory"), new HBox(inventoryField, addButton), inventoryTable, deleteButton,
                saveButton);

        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);

        PopUp<ScrollPane, ?> popUp = PopUp.<ScrollPane>create()
                .withTitle("Add goals to your player")
                .withoutCloseButton()
                .withContent(scrollPane)
                .withDialogSize(400, 500);

        saveButton.setOnAction(e -> {
            if(healthField.getText().isBlank() || goldField.getText().isBlank()) {
                AlertDialog.showWarning("The different fields cannot be blank.");
            } else {
                INSTANCE.changeGoal(GoalFactory.getGoal(GoalType.HEALTH_GOAL, healthField.getText()));
                INSTANCE.changeGoal(GoalFactory.getGoal(GoalType.GOLD_GOAL, goldField.getText()));
                INSTANCE.changeGoal(GoalFactory.getGoal(GoalType.SCORE_GOAL, scoreField.getText()));
                INSTANCE.changeGoal(GoalFactory.getGoal(GoalType.INVENTORY_GOAL, items));

                popUp.close();
            }
        });
        popUp.showAndWait();
    }
}
