package edu.ntnu.idatt2001.group_30.paths.view.components.pop_up;

import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.LinkTable;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.TableDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *  This class contains a pop-up for creating a new passage.
 *
 * @author Trym Hamer Gudvangen
 */
public class PassagePopUp extends AbstractPopUp {

    private TextField titleField;
    private TextArea contentArea;
    private Button saveButton;
    private Button removeLinkButton;
    private Button addLinkButton;
    private Button editLinkButton;
    private VBox content;
    private LinkTable<Link> linkTable;
    private final ObservableList<Passage> passages;
    private final ObservableList<Link> links;
    private Passage passage;
    private PopUp<VBox, ?> popUp;

    /**
     * This constructor allows a new passage to be created.
     * @param passages Other passages in the story, given as an ObservableList of passages.
     */
    public PassagePopUp(ObservableList<Passage> passages) {
        this.passages = passages;
        this.links = FXCollections.observableArrayList();
        initialize();
        createPopUp();
    }

    /**
     * This constructor allows a pre-existing passage to be edited.
     * @param passages  Other passages in the story, given as an ObservableList of passages.
     * @param passage   Passage to edit, given as a Passage object.
     */
    public PassagePopUp(ObservableList<Passage> passages, Passage passage) {
        this.passages = passages;
        this.passage = passage;
        this.links = FXCollections.observableArrayList(passage.getLinks());
        initialize();
        loadPassage(passage);
        createPopUp();
    }

    /**
     * This method sets up the UI components for the pop-up.
     */
    @Override
    protected void setupUiComponents() {
        titleField = new TextField();
        titleField.setPromptText("Enter the title of the passage");

        contentArea = new TextArea();
        contentArea.setPromptText("Enter the content of the passage");
        contentArea.setMinHeight(150);

        saveButton = new Button("Save");

        linkTable =
            new LinkTable<>(
                new TableDisplay.Builder<Link>().addColumn("Link Title", "text").addColumn("Reference", "reference")
            );

        linkTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        linkTable.setItems(links);

        editLinkButton = new Button("Edit Link");
        editLinkButton.setDisable(true);

        removeLinkButton = new Button("Remove Link");
        removeLinkButton.setDisable(true);

        addLinkButton = new Button("Add Link");
        if (passages.isEmpty()) addLinkButton.setDisable(true);

        HBox linkTableButtonHBox = new HBox(editLinkButton, addLinkButton, removeLinkButton);
        linkTableButtonHBox.setAlignment(Pos.CENTER);

        content =
            new VBox(
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
    }

    /**
     * This method sets up the behavior for the pop-up.
     */
    @Override
    protected void setupBehavior() {
        editLinkButton.setOnAction(e -> {
            Link newLink = new LinkPopUp(this.passages, linkTable.getSelectionModel().getSelectedItem()).getLink();
            if (newLink != null) {
                this.links.remove(linkTable.getSelectionModel().getSelectedItem());
                this.links.add(newLink);
            }
        });

        removeLinkButton.setOnAction(e -> links.remove(linkTable.getSelectionModel().getSelectedItem()));
        linkTable
            .getSelectionModel()
            .selectedItemProperty()
            .addListener((obs, oldSelection, newSelection) -> {
                removeLinkButton.setDisable(newSelection == null);
                editLinkButton.setDisable(newSelection == null);
            });

        addLinkButton.setOnAction(e -> {
            Link newLink = new LinkPopUp(this.passages).getLink();
            if (newLink != null) {
                this.links.add(newLink);
            }
        });

        saveButton.setOnAction(e -> {
            if (titleField.getText().isBlank() || contentArea.getText().isBlank()) {
                AlertDialog.showWarning("The title or content cannot be blank.");
            } else if (
                this.passages.stream()
                    .anyMatch(passage1 -> passage1.getTitle().equals(titleField.getText()) && passage != passage1)
            ) {
                AlertDialog.showWarning("A passage with the title " + titleField.getText() + " already exists.");
            } else {
                this.passage = new Passage(titleField.getText(), contentArea.getText());

                this.links.forEach(link -> this.passage.addLink(link));
                popUp.close();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createPopUp() {
        popUp =
            PopUp
                .<VBox>create()
                .withTitle("Create a Passage")
                .withoutCloseButton()
                .withContent(content)
                .withDialogSize(400, 750);

        popUp.showAndWait();
    }

    /**
     * This method loads a passage into the pop-up.
     * @param passage   Passage to load, given as a Passage object.
     */
    private void loadPassage(Passage passage) {
        titleField.setText(passage.getTitle());
        contentArea.setText(passage.getContent());
    }

    /**
     * This method retrieves the passages created from the pop-up.
     * @return  Passages created, given as a List of Passage objects.
     */
    public Passage getPassage() {
        return passage;
    }
}
