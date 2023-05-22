package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.actions.Action;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class LinkPopUp {

    private TextField textField;
    private Button saveButton;
    private ObservableList<Passage> passages;
    private ObservableList<Link> links;
    private HashMap<String, Passage> passageHashMap;
    private Link link;

    public LinkPopUp(ObservableList<Passage> passages, ObservableList<Link> links) {
        this.passages = passages;
        this.links = links;
        this.passageHashMap = new HashMap<>();

        textField = new TextField();
        textField.setPromptText("Enter the text of the link");


        passages.forEach(passage -> passageHashMap.put(passage.getTitle(), passage));
        ComboBox<String> reference = new ComboBox<>(FXCollections.observableArrayList(passageHashMap.keySet()));
        reference.setPromptText("Select reference passage of the link");

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
                reference,
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
            if (textField.getText().isBlank() || reference.getValue() == null) {
                AlertDialog.showWarning("The text or reference cannot be blank.");
            } else {
                link = new Link(textField.getText(), passageHashMap.get(reference.getValue()).getTitle());
                popUp.close();
            }
        });

        popUp.showAndWait();
    }

    /**
     * This method retrieves the link created in the pop-up.
     * @return  Link created in pop-up, given as a Link object.
     */
    public Link getLink() {
        return link;
    }
}
