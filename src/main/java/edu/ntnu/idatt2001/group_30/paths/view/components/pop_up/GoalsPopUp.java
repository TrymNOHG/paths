package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.model.goals.GoalFactory;
import edu.ntnu.idatt2001.group_30.paths.model.goals.GoalType;
import edu.ntnu.idatt2001.group_30.paths.model.utils.TextValidation;
import java.net.URL;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GoalsPopUp extends AbstractPopUp{

    private TextField healthField;
    private TextField goldField;
    private TextField scoreField;
    private Button saveButton;
    private Button addButton;
    private Button deleteButton;
    private TextField inventoryField;
    private ObservableList<String> items;
    private TableView<String> inventoryTable;
    private VBox content;
    private ScrollPane scrollPane;
    private PopUp<ScrollPane, ?> popUp;

    public GoalsPopUp() {
        initialize();
        createPopUp();
    }

    @Override
    protected void setupUiComponents() {
        healthField = new TextField();
        healthField.setTextFormatter(
                TextValidation.createIntegerTextFormatter(
                        INSTANCE.getHealthGoal() == null ? 100 : INSTANCE.getHealthGoal().getGoalValue()
                )
        );
        healthField.setPromptText("Add health goal");

        goldField = new TextField();
        goldField.setTextFormatter(
                TextValidation.createIntegerTextFormatter(
                        INSTANCE.getGoldGoal() == null ? 100 : INSTANCE.getGoldGoal().getGoalValue()
                )
        );
        goldField.setPromptText("Add gold goal");

        scoreField = new TextField();
        scoreField.setTextFormatter(
                TextValidation.createIntegerTextFormatter(
                        INSTANCE.getScoreGoal() == null ? 100 : INSTANCE.getScoreGoal().getGoalValue()
                )
        );
        scoreField.setPromptText("Add score goal");

        saveButton = new Button("Save");

        inventoryField = new TextField();
        inventoryField.setPromptText("Add inventory item");

        addButton = new Button();
        URL imageUrl = getClass().getResource("/images/plus.png");
        if (imageUrl != null) {
            ImageView addIcon = new ImageView(new Image(imageUrl.toString()));
            addIcon.setFitHeight(25);
            addIcon.setFitWidth(25);
            addButton.setGraphic(addIcon);
        } else {
            System.err.println("Something is wrong with the trash image resource link");
        }

        items = FXCollections.observableArrayList();
        if (INSTANCE.getInventoryGoal() != null) {
            items.addAll(INSTANCE.getInventoryGoal().getGoalValue());
        }
        inventoryTable = new TableView<>(items);
        TableColumn<String, String> itemColumn = new TableColumn<>("Items");
        itemColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        inventoryTable.getColumns().add(itemColumn);
        itemColumn.prefWidthProperty().bind(inventoryTable.widthProperty());
        inventoryTable.setMaxHeight(200);

        deleteButton = new Button();
        imageUrl = getClass().getResource("/images/trash.png");
        if (imageUrl != null) {
            ImageView trashIcon = new ImageView(new Image(imageUrl.toString()));
            trashIcon.setFitHeight(25);
            trashIcon.setFitWidth(25);
            deleteButton.setGraphic(trashIcon);
        } else {
            System.err.println("Something is wrong with the trash image resource link");
        }

        content = new VBox(
                new Label("Health:"),
                healthField,
                new Label("Gold:"),
                goldField,
                new Label("Score:"),
                scoreField,
                new Label("Inventory"),
                new HBox(inventoryField, addButton),
                inventoryTable,
                deleteButton,
                saveButton
        );

        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);

        scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
    }

    @Override
    protected void setupBehavior() {
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

        saveButton.setOnAction(e -> {
            if (healthField.getText().isBlank() || goldField.getText().isBlank()) {
                AlertDialog.showWarning("The different fields cannot be blank.");
            } else {
                INSTANCE.changeGoal(GoalFactory.getGoal(GoalType.HEALTH_GOAL, healthField.getText()));
                INSTANCE.changeGoal(GoalFactory.getGoal(GoalType.GOLD_GOAL, goldField.getText()));
                INSTANCE.changeGoal(GoalFactory.getGoal(GoalType.SCORE_GOAL, scoreField.getText()));
                INSTANCE.changeGoal(GoalFactory.getGoal(GoalType.INVENTORY_GOAL, items));

                popUp.close();
            }
        });

    }

    @Override
    protected void createPopUp() {
        popUp = PopUp
                .<ScrollPane>create()
                .withTitle("Add goals to your player")
                .withoutCloseButton()
                .withContent(scrollPane)
                .withDialogSize(400, 750);


        popUp.showAndWait();
    }
}
