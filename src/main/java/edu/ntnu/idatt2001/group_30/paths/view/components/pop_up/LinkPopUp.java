package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.actions.Action;
import edu.ntnu.idatt2001.group_30.paths.model.actions.ActionFactory;
import edu.ntnu.idatt2001.group_30.paths.model.actions.ActionType;
import edu.ntnu.idatt2001.group_30.paths.model.utils.TextValidation;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.ActionTable;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.TableDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class LinkPopUp extends AbstractPopUp{

    private TextField textField;
    private TextField actionTextField;
    private Button saveButton;
    private Button addActionButton;
    private Button removeActionButton;
    private ComboBox<String> reference;
    private ComboBox<ActionType> actionComboBox;
    private ActionTable<Action<?>> actionTable;
    private VBox content;
    private final ObservableList<Passage> passages;
    private ObservableList<Action<?>> actions;
    private HashMap<String, Passage> passageHashMap;
    private Link link;
    private PopUp<VBox, ?> popUp;

    public LinkPopUp(ObservableList<Passage> passages) {
        this.actions = FXCollections.observableArrayList();
        this.passages = passages;
        this.passageHashMap = new HashMap<>();
        passages.forEach(passage -> passageHashMap.put(passage.getTitle(), passage));

        initialize();
        createPopUp();
    }

    public LinkPopUp(ObservableList<Passage> passages, Link link) {
        this.link = link;
        this.actions = FXCollections.observableArrayList(link.getActions());
        this.passages = passages;
        this.passageHashMap = new HashMap<>();
        passages.forEach(passage -> passageHashMap.put(passage.getTitle(), passage));

        initialize();
        loadLink();
        createPopUp();
    }


    @Override
    protected void setupUiComponents() {
        textField = new TextField();
        textField.setPromptText("Enter the text of the link");

        reference = new ComboBox<>(FXCollections.observableArrayList(passageHashMap.keySet()));
        reference.setPromptText("Select reference passage of the link");

        saveButton = new Button("Save");

        ObservableList<ActionType> actionTypes = FXCollections.observableArrayList(ActionType.values());

        actionComboBox = new ComboBox<>(actionTypes);
        actionComboBox.setPromptText("Select an action");

        actionTextField = new TextField();


        removeActionButton = new Button("Remove Action");
        removeActionButton.setDisable(true);


        addActionButton = new Button("Add Action");

        HBox actionHbox = new HBox(actionComboBox, actionTextField, addActionButton);
        actionHbox.setAlignment(Pos.CENTER);

        actionTable = new ActionTable<>(new TableDisplay.Builder<Action<?>>()
                .addColumnWithComplexValue("Type", action -> action.getClass().getSimpleName())
                .addColumnWithComplexValue("Value", action -> action.getActionValue().toString()));

        actionTable.setItems(actions);

        actionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        content = new VBox(
                new Label("Link Text:"),
                textField,
                new Label("Link Reference:"),
                reference,
                new Label("Actions:"),
                actionHbox,
                actionTable,
                removeActionButton,
                saveButton
        );

        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);


    }

    @Override
    protected void setupBehavior() {

        removeActionButton.setOnAction(event -> actions.remove(actionTable.getSelectionModel().getSelectedItem()));

        actionTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            removeActionButton.setDisable(newSelection == null);
        });


        addActionButton.setOnAction(e -> {
            if (actionComboBox.getValue() != null) {
                actions.add(ActionFactory.getAction(actionComboBox.getValue(), actionTextField.getText()));
                actionComboBox.setValue(null);
            }
        });

        saveButton.setOnAction(e -> {
            if (textField.getText().isBlank() || reference.getValue() == null) {
                AlertDialog.showWarning("The text or reference cannot be blank.");
            } else {
                link = new Link(textField.getText(), passageHashMap.get(reference.getValue()).getTitle());
                actions.forEach(action -> link.addAction(action));
                popUp.close();
            }
        });

        actionComboBox.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(ActionType actionType, boolean empty) {
                super.updateItem(actionType, empty);
                if (empty || actionType == null) {
                    setText(null);
                } else {
                    setText(actionType.getDisplayName());
                    switch(actionType) {
                        case SCORE_ACTION, GOLD_ACTION, HEALTH_ACTION -> actionTextField.setTextFormatter(TextValidation.createIntegerTextFormatter());
                        case INVENTORY_ACTION -> actionTextField.setTextFormatter(null);
                    }
                }
            }
        });

        actionComboBox.setButtonCell(actionComboBox.getCellFactory().call(null));
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

    private void loadLink() {
        textField.setText(this.link.getText());
        reference.setValue(this.link.getReference());
    }

    /**
     * This method retrieves the link created in the pop-up.
     * @return  Link created in pop-up, given as a Link object.
     */
    public Link getLink() {
        return link;
    }
}
