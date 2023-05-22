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

public class LinkPopUp extends AbstractPopUp{

    private TextField textField;
    private Button saveButton;
    private Button addActionButton;
    private ComboBox<String> reference;
    private ComboBox<Action<?>> actionComboBox;
    private VBox content;
    private final ObservableList<Passage> passages;
    private final ObservableList<Link> links;
    private HashMap<String, Passage> passageHashMap;
    private Link link;
    private PopUp<VBox, ?> popUp;

    public LinkPopUp(ObservableList<Passage> passages, ObservableList<Link> links) {
        this.passages = passages;
        this.links = links;
        this.passageHashMap = new HashMap<>();
        passages.forEach(passage -> passageHashMap.put(passage.getTitle(), passage));

        initialize();
        createPopUp();
    }


    @Override
    protected void setupUiComponents() {
        textField = new TextField();
        textField.setPromptText("Enter the text of the link");

        reference = new ComboBox<>(FXCollections.observableArrayList(passageHashMap.keySet()));
        reference.setPromptText("Select reference passage of the link");

        saveButton = new Button("Save");

        actionComboBox = new ComboBox<>(null);
        addActionButton = new Button("Add Action");

        content = new VBox(
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


    }

    @Override
    protected void setupBehavior() {
        addActionButton.setOnAction(e -> {
            if (actionComboBox.getValue() != null) {
                //TODO: add the action to the link
                actionComboBox.setValue(null);
            }
        });

        saveButton.setOnAction(e -> {
            if (textField.getText().isBlank() || reference.getValue() == null) {
                AlertDialog.showWarning("The text or reference cannot be blank.");
            } else {
                link = new Link(textField.getText(), passageHashMap.get(reference.getValue()).getTitle());
                popUp.close();
            }
        });
    }

    @Override
    protected void createPopUp() {
        popUp = PopUp
                .<VBox>create()
                .withTitle("Create a Link")
                .withoutCloseButton()
                .withContent(content)
                .withDialogSize(400, 500);

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
