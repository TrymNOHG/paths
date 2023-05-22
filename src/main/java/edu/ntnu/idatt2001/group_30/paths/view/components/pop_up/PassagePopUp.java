package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.actions.Action;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.LinkTable;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.PassageTable;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.TableDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;

/**
 *  This class contains a pop-up for creating a new passage.
 *
 * @author Trym Hamer Gudvangen
 */
public class PassagePopUp {

    private final TextField titleField;
    private final TextArea contentArea;
    private final Button saveButton;
    private final Button removeLinkButton;
    private final LinkTable<Link> linkTable;
    private final ObservableList<Passage> passages;
    private final ObservableList<Link> links;
    private Passage passage;

    public PassagePopUp(ObservableList<Passage> passages) {
        this.passages = passages;
        this.links = FXCollections.observableArrayList();

        titleField = new TextField();
        titleField.setPromptText("Enter the title of the passage");

        contentArea = new TextArea();
        contentArea.setPromptText("Enter the content of the passage");

        saveButton = new Button("Save");

        linkTable = new LinkTable<>(new TableDisplay.Builder<Link>()
                .addColumn("Link Title", "text")
                .addColumn("Reference", "reference"));

        linkTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        linkTable.setItems(links);

        removeLinkButton = new Button("Remove Link");
        removeLinkButton.setDisable(true);

        removeLinkButton.setOnAction(e -> links.remove(linkTable.getSelectionModel().getSelectedItem()));
        linkTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
                removeLinkButton.setDisable(newSelection == null));

        Button addLinkButton = new Button("Add Link");
        HBox linkTableButtonHBox = new HBox(addLinkButton, removeLinkButton);
        linkTableButtonHBox.setAlignment(Pos.CENTER);

        addLinkButton.setOnAction(e -> this.links.add(new LinkPopUp(passages, links).getLink()));

        VBox content = new VBox(
                new Label("Passage Title:"),
                titleField,
                new Label("Passage Content:"),
                contentArea,
                new Label("Links:"),
                linkTable,
                linkTableButtonHBox,
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
        ;

        saveButton.setOnAction(e -> {
            if (titleField.getText().isBlank() || contentArea.getText().isBlank()) {
                AlertDialog.showWarning("The title or content cannot be blank.");
            } else {
                this.passage = new Passage(titleField.getText(), contentArea.getText());

                //TODO: save the new passage
                //TODO: add links from the table to the new passage

                popUp.close();
            }
        });

        popUp.showAndWait();
    }

    /**
     * This method retrieves the passages created from the pop-up.
     * @return  Passags created, given as a List of Passage objects.
     */
    public Passage getPassage() {
        return passage;
    }
}
