package edu.ntnu.idatt2001.group_30.paths.view.views;

import edu.ntnu.idatt2001.group_30.paths.controller.NewStoryController;
import edu.ntnu.idatt2001.group_30.paths.controller.StageManager;
import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultText;
import edu.ntnu.idatt2001.group_30.paths.view.components.pop_up.AlertDialog;
import edu.ntnu.idatt2001.group_30.paths.view.components.pop_up.PassagePopUp;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.PassageTable;
import edu.ntnu.idatt2001.group_30.paths.view.components.table.TableDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.stream.Collectors;

public class NewStoryView extends View<BorderPane> {

    private final NewStoryController newStoryController;
    private String title = "";
    private Story story;
    private final ObservableList<Passage> passages;
    private final Button removePassageButton;
    private final Button editPassageButton;


    public NewStoryView() {
        super(BorderPane.class);

        newStoryController = new NewStoryController();

        if (INSTANCE.getStory() != null) {
            story = INSTANCE.getStory();
        }

        if(story != null) title = story.getTitle();


        passages = story == null ? FXCollections.observableArrayList() :
                FXCollections.observableArrayList(story.getPassages());
        Text titleText = DefaultText.big("Create a new/edit a Story");

        Text labelText = new Text("Story Title: ");
        TextField textField = new TextField(title);
        textField.setPromptText("Enter story title");


        HBox titleBox = new HBox(labelText, textField);
        titleBox.setSpacing(20);

        textField.setOnKeyTyped(event -> title = textField.getText());

        titleBox.setAlignment(Pos.CENTER);



        PassageTable<Passage> passageTable = new PassageTable<>(new TableDisplay.Builder<Passage>()
                .addColumn("Name of Passage", "title")
                .addColumn("Passage Content", "content")
                .addColumnWithComplexValue("Links", passage -> passage == null ?
                        null :
                        passage.getLinks().stream()
                            .map(Link::getText)
                            .collect(Collectors.joining(", "))
                ));

        passageTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        passageTable.setItems(passages);
        passageTable.setMaxWidth(1000);

        removePassageButton = new Button("Remove Passage");
        removePassageButton.setDisable(true);
        removePassageButton.setOnAction(e -> {
            passages.forEach(passage -> passage.getLinks().removeIf(link ->
                    Objects.equals(link.getReference(), passageTable.getSelectionModel().getSelectedItem().getTitle())));
            passages.remove(passageTable.getSelectionModel().getSelectedItem());
        });

        editPassageButton = new Button("Edit Passage");
        editPassageButton.setDisable(true);
        editPassageButton.setOnAction(e -> {
            Passage selectedPassage = passageTable.getSelectionModel().getSelectedItem();
            if (selectedPassage != null) {
                Passage updatedPassage = new PassagePopUp(passages, selectedPassage).getPassage();
                if(updatedPassage != null && !selectedPassage.equals(updatedPassage)) {
                    passages.forEach(passage -> {
                        System.out.println(passage.getTitle());
                        System.out.println(passage.getLinks());
                        passage.getLinks().replaceAll(link ->
                                link.getReference().equals(selectedPassage.getTitle()) ?
                                new Link(link.getText(), updatedPassage.getTitle()) : link
                        );
                    });
                    passages.remove(selectedPassage);
                    passages.add(updatedPassage);
                }
            }
        });

        passageTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            removePassageButton.setDisable(newSelection == null);
            editPassageButton.setDisable(newSelection == null);
        });


        Button addPassageButton = new Button();
        URL imageUrl = getClass().getResource("/images/plus.png");
        if (imageUrl != null) {
            ImageView addIcon = new ImageView(new Image(imageUrl.toString()));
            addIcon.setFitHeight(25);
            addIcon.setFitWidth(25);
            addPassageButton.setGraphic(addIcon);
        } else {
            System.err.println("Something is wrong with the trash image resource link");
        }

        VBox editTableButtons = new VBox(addPassageButton, removePassageButton, editPassageButton);
        editTableButtons.setAlignment(Pos.CENTER);
        editTableButtons.setSpacing(20);

        addPassageButton.setOnAction(event -> {
            if(passages.isEmpty()) {
                AlertDialog.showInformation("Every story needs an opening passage.", "The opening passage" +
                        " will by default be the first passage added.");
            }
            PassagePopUp passagePopUp = new PassagePopUp(passages);
            if(passagePopUp.getPassage() != null) this.passages.addAll(passagePopUp.getPassage());
        });

        Button saveButton = new Button("Save Story");
        saveButton.setOnAction(event -> {
            try {
                newStoryController.addStory(title, passages);
                StageManager.getInstance().setCurrentView(new LoadGameView());
            }
            catch (Exception ex) {
                AlertDialog.showWarning(ex.getMessage());
            }
        });

        VBox display = new VBox(titleText, titleBox, passageTable, saveButton);
        display.setAlignment(Pos.CENTER);
        display.setSpacing(10);
        display.setPrefWidth(500);

        Button backButton = new Button("Back");
        backButton.setOnAction(newStoryController.goBack());

        getParentPane().setCenter(display);
        getParentPane().setBottom(backButton);
        getParentPane().setRight(editTableButtons);
        getParentPane().getRight().setTranslateX(-50);
    }

}
