package edu.ntnu.idatt2001.group_30.paths.view.views;

import edu.ntnu.idatt2001.group_30.paths.controller.NewStoryController;
import edu.ntnu.idatt2001.group_30.paths.controller.StageManager;
import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;


import edu.ntnu.idatt2001.group_30.paths.model.filehandling.StoryFileHandler;
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
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

//TODO: Add message that first passage will be opening passage

public class NewStoryView extends View<BorderPane> {

    private final NewStoryController newStoryController;
    private String title = "";

    private Story story;

    private final ObservableList<Passage> passages;

    public NewStoryView() {
        super(BorderPane.class);
        newStoryController = new NewStoryController();

        if (INSTANCE.getStory() != null) {
            story = INSTANCE.getStory();
        }

        if(story != null) title = story.getTitle();


        passages = story == null ? FXCollections.observableArrayList() :
                FXCollections.observableArrayList(story.getPassages());
        Text titleText = new Text("Create a new/edit a Story");

        Text labelText = new Text("Story Title: ");
        TextField textField = new TextField(title);
        textField.setPromptText("Enter story title");


        HBox titleBox = new HBox(labelText, textField);

        textField.setOnKeyTyped(event -> {
            title = textField.getText();
        });

        titleBox.setAlignment(Pos.CENTER);



        PassageTable<Passage> passageTable = new PassageTable<>(new TableDisplay.Builder<Passage>()
                .addColumn("Name of Passage", "title")
                .addColumn("Passage Content", "content")
                .addColumnWithComplexValue("Links", passage -> passage.getLinks().stream()
                        .map(Link::getText)
                        .collect(Collectors.joining(", "))));

        passageTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        passageTable.setItems(passages);
        passageTable.setMaxWidth(1000);

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

        addPassageButton.setOnAction(event -> new PassagePopUp());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            try {

                //TODO: if everything goes right, give a little feedback and
                //      then take back to load with story selected
                // Add passage with links
                newStoryController.addStory(title, passages);

            } catch (Exception ex) {
                AlertDialog.showWarning(ex.getMessage());
            }
            StageManager.getInstance().setCurrentView(new LoadGameView());
        });

        VBox display = new VBox(titleText, titleBox, passageTable, addPassageButton, saveButton);
        display.setAlignment(Pos.CENTER);
        display.setSpacing(10);
        display.setPrefWidth(500);

        Button backButton = new Button("Back");
        backButton.setOnAction(newStoryController.goBack());

        getParentPane().setCenter(display);
        getParentPane().setBottom(backButton);


    }



}
