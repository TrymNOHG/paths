package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PassagePopUp {

    private TextField titleField;
    private TextArea contentArea;
    private Button saveButton;



    public PassagePopUp() {
        titleField = new TextField();
        titleField.setPromptText("Enter the title of the passage");

        contentArea = new TextArea();
        contentArea.setPromptText("Enter the content of the passage");

        saveButton = new Button("Save");

        VBox content = new VBox(
                new Label("Passage Title:"),
                titleField,
                new Label("Passage Content:"),
                contentArea,
                saveButton
        );

        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);

        PopUp<VBox, ?> popUp = PopUp
                .<VBox>create()
                .withTitle("Create a Passage")
                .withoutCloseButton()
                .withContent(content)
                .withDialogSize(400, 500);

        saveButton.setOnAction(e -> {
            if (titleField.getText().isBlank() || contentArea.getText().isBlank()) {
                AlertDialog.showWarning("The title or content cannot be blank.");
            } else {
                Passage newPassage = new Passage(titleField.getText(), contentArea.getText());

                //TODO: save the new passage
                popUp.close();
            }
        });

        popUp.showAndWait();
    }
}
