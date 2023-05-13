package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.CreatePlayerController;
import edu.ntnu.idatt2001.group_30.paths.controller.StageManager;
import edu.ntnu.idatt2001.group_30.paths.view.components.pop_up.GoalsPopUp;
import edu.ntnu.idatt2001.group_30.paths.view.components.pop_up.StatsPopUp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CreatePlayerView extends View<BorderPane> {

    private final CreatePlayerController createPlayerController;
    private TextField nameField;
    private Button continueButton, returnButton;

    public CreatePlayerView() {
        super(BorderPane.class);

        createPlayerController = new CreatePlayerController();

        // Top: Title
        Text title = new Text("Create Your Player");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        VBox topBox = new VBox(title);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20, 0, 20, 0));
        getParentPane().setTop(topBox);

        // Center: Player Image Carousel
        // Here you would use a more complex control than an ImageView, but this is just an example.
        ImageView playerView = new ImageView();
        VBox centerBox = new VBox(playerView);
        centerBox.setAlignment(Pos.CENTER);
        getParentPane().setCenter(centerBox);

        // Left: Stats and Goals
        Button statsButton = new Button("Stats");
        Button goalsButton = new Button("Goals");
        VBox leftBox = new VBox(statsButton, goalsButton);
        leftBox.setSpacing(20);
        leftBox.setPadding(new Insets(0, 20, 0, 20));
        getParentPane().setLeft(leftBox);

        // Stats pop up
        statsButton.setOnAction(e -> new StatsPopUp());

        // Goals pop up
        goalsButton.setOnAction(e -> new GoalsPopUp());

        // Bottom: Name and Continue
        nameField = new TextField();
        nameField.setPromptText("Enter your name");
        continueButton = new Button("Continue");
        returnButton = new Button("Return");
        HBox bottomBox = new HBox(nameField, continueButton, returnButton);
        bottomBox.setSpacing(20);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20, 0, 20, 0));
        getParentPane().setBottom(bottomBox);

        //TODO: include error handling
        continueButton.setOnAction(createPlayerController.goTo(NewGameView.class));
        returnButton.setOnAction(e -> StageManager.getInstance().goBack());
    }
}
