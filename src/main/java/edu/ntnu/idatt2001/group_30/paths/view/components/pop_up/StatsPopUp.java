package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.model.utils.TextValidation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StatsPopUp extends AbstractPopUp{

    private TextField healthField;
    private TextField goldField;
    private Button saveButton;
    private VBox content;
    private PopUp<VBox, ?> popUp;

    public StatsPopUp() {
        initialize();
        createPopUp();
    }

    public int getHealth() {
        return Integer.parseInt(healthField.getText());
    }

    public int getGold() {
        return Integer.parseInt(goldField.getText());
    }

    @Override
    protected void setupUiComponents() {
        healthField = new TextField();
        healthField.setTextFormatter(TextValidation.createIntegerTextFormatter(INSTANCE.getPlayer().getHealth()));

        healthField.setPromptText("Add health value");

        goldField = new TextField();
        goldField.setTextFormatter(TextValidation.createIntegerTextFormatter(INSTANCE.getPlayer().getGold()));
        goldField.setPromptText("Add gold value");

        saveButton = new Button("Save");

        content = new VBox(new Label("Health:"), healthField, new Label("Gold:"), goldField, saveButton);
        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);
    }

    @Override
    protected void setupBehavior() {
        saveButton.setOnAction(e -> {
            if (healthField.getText().isBlank() || goldField.getText().isBlank()) {
                AlertDialog.showWarning("The different fields cannot be blank.");
            } else {
                INSTANCE.getPlayer().setHealth(Integer.parseInt(healthField.getText()));
                INSTANCE.getPlayer().setGold(Integer.parseInt(goldField.getText()));
                popUp.close();
            }
        });
    }

    @Override
    protected void createPopUp() {
        popUp = PopUp
                .<VBox>create()
                .withTitle("Add stats to your player")
                .withoutCloseButton()
                .withContent(content)
                .withDialogSize(400, 300);

        popUp.showAndWait();
    }
}
