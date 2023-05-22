package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.actions.Action;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LinkPopUp {

    private TextField textField;
    private TextField referenceField;
    private Button saveButton;
    private ObservableList<Passage> passages;
    private ObservableList<Link> links;

    private Link link;

    public LinkPopUp(ObservableList<Passage> passages, ObservableList<Link> links) {
        this.passages = passages;
        this.links = links;

        textField = new TextField();
        textField.setPromptText("Enter the text of the link");

        referenceField = new TextField();
        referenceField.setPromptText("Enter the reference of the link");

        saveButton = new Button("Save");

        ComboBox<Action<?>> actionComboBox = new ComboBox<>(null);
        Button addActionButton = new Button("Add Action");
        addActionButton.setOnAction(e -> {
            if (actionComboBox.getValue() != null) {
                //TODO: add the action to the link
                actionComboBox.setValue(null);
            }
        });

        VBox content = new VBox(
                new Label("Link Text:"),
                textField,
                new Label("Link Reference:"),
                referenceField,
                new Label("Actions:"),
                actionComboBox,
                addActionButton,
                saveButton
        );

        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);

        PopUp<VBox, ?> popUp = PopUp
                .<VBox>create()
                .withTitle("Create a Link")
                .withoutCloseButton()
                .withContent(content)
                .withDialogSize(400, 500);

        saveButton.setOnAction(e -> {
            if (textField.getText().isBlank() || referenceField.getText().isBlank()) {
                AlertDialog.showWarning("The text or reference cannot be blank.");
            } else {
                link = new Link(textField.getText(), referenceField.getText());

                popUp.close();
            }
        });

        popUp.showAndWait();
    }

    public Link getLink() {
        return link;
    }
}
