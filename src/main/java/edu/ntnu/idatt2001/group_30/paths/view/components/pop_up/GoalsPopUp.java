package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

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

public class GoalsPopUp {

    private TextField healthField;
    private TextField goldField;
    private TextField scoreField;
    private Button saveButton;

    public GoalsPopUp() {
        healthField = new TextField();
        healthField.setTextFormatter(TextValidation.createIntegerTextFormatter());
        healthField.setPromptText("Add health goal");

        goldField = new TextField();
        goldField.setTextFormatter(TextValidation.createIntegerTextFormatter());
        goldField.setPromptText("Add gold goal");

        scoreField = new TextField();
        scoreField.setTextFormatter(TextValidation.createIntegerTextFormatter());
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
        scrollPane.setFitToWidth(true);  // If you want the content to always fill the width of the scroll pane

        PopUp<ScrollPane, ?> popUp = PopUp.<ScrollPane>create()
                .withTitle("Add goals to your player")
                .withoutCloseButton()
                .withContent(scrollPane)
                .withDialogSize(400, 500);

        saveButton.setOnAction(e -> {
            if(healthField.getText().isBlank() || goldField.getText().isBlank()) {
                AlertDialog.showWarning("The different fields cannot be blank.");
            } else {
                popUp.close();
            }
        });
        popUp.showAndWait();
    }
}
