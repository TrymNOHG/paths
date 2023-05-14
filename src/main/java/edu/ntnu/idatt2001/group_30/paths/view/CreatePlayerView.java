package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.controller.CreatePlayerController;
import edu.ntnu.idatt2001.group_30.paths.controller.StageManager;
import edu.ntnu.idatt2001.group_30.paths.model.Player;
import edu.ntnu.idatt2001.group_30.paths.view.components.ImageCarousel;
import edu.ntnu.idatt2001.group_30.paths.view.components.pop_up.AlertDialog;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static edu.ntnu.idatt2001.group_30.paths.PathsSingleton.INSTANCE;

public class CreatePlayerView extends View<BorderPane> {

    private final CreatePlayerController createPlayerController;
    private TextField nameField;
    private Button continueButton, returnButton;

    public CreatePlayerView() {
        super(BorderPane.class);

        createPlayerController = new CreatePlayerController();

        Text title = new Text("Create Your Player");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        VBox topBox = new VBox(title);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20, 0, 20, 0));
        getParentPane().setTop(topBox);

        getParentPane().getStyleClass().add("create-player-view");
        getParentPane().getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());


        Button statsButton = new Button("Stats");
        URL imageUrl = getClass().getResource("/images/stats.png");
        if (imageUrl != null) {
            ImageView imageView = new ImageView(imageUrl.toString());
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            statsButton.setGraphic(imageView);
        } else {
            System.err.println("Unable to load image: /images/stats.png");
        }

        Button goalsButton = new Button("Goals");
        imageUrl = getClass().getResource("/images/goals.png");
        if (imageUrl != null) {
            ImageView imageView = new ImageView(imageUrl.toString());
            imageView.setFitWidth(90);
            imageView.setFitHeight(90);
            goalsButton.setGraphic(imageView);
        } else {
            System.err.println("Unable to load image: /images/stats.png");
        }

        statsButton.getStyleClass().add("stats-button");
        goalsButton.getStyleClass().add("goals-button");

        VBox leftVBox = new VBox(statsButton, goalsButton);
        leftVBox.setSpacing(20);
        leftVBox.setPadding(new Insets(300, 20, 0, 20));
        HBox leftHBox = new HBox(leftVBox);
        leftHBox.setAlignment(Pos.CENTER_LEFT);
        getParentPane().setLeft(leftHBox);

        statsButton.setOnAction(e -> new StatsPopUp());

        goalsButton.setOnAction(e -> new GoalsPopUp());

        List<String> headURIs = new ArrayList<>();
        headURIs.add("/images/RedHair.png");
        headURIs.add("/images/BlueHair.png");
        headURIs.add("/images/GreenHair.png");

        List<String> torsoURIs = new ArrayList<>();
        torsoURIs.add("/images/RedTorso.png");
        torsoURIs.add("/images/BlueTorso.png");
        torsoURIs.add("/images/GreenTorso.png");

        List<String> legsURIs = new ArrayList<>();
        legsURIs.add("/images/RedLegs.png");
        legsURIs.add("/images/BlueLegs.png");
        legsURIs.add("/images/GreenLegs.png");

        ImageCarousel headCarousel = new ImageCarousel(headURIs);
        ImageCarousel headCarousel2 = new ImageCarousel(torsoURIs);
        ImageCarousel headCarousel3 = new ImageCarousel(legsURIs);
        VBox centerBox = new VBox(headCarousel.getCarousel(), headCarousel2.getCarousel(),
                headCarousel3.getCarousel());
        centerBox.setAlignment(Pos.CENTER);

        leftVBox.getStyleClass().add("left-vbox");


        nameField = new TextField();
        if(Objects.equals(INSTANCE.getPlayer().getName(), "Default")) {
            nameField.setPromptText("Enter your name");
        } else {
            nameField.setText(INSTANCE.getPlayer().getName());
        }
        nameField.setMinWidth(200);

        continueButton = new Button("Continue");
        returnButton = new Button("Return");
        HBox bottomBox = new HBox(nameField, continueButton, returnButton);
        bottomBox.setSpacing(20);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(0, 0, 0, 0));
        centerBox.getChildren().add(bottomBox);
        getParentPane().setCenter(centerBox);

        centerBox.getStyleClass().add("center-box");
        nameField.getStyleClass().add("name-field");

        continueButton.getStyleClass().add("continue-button");
        returnButton.getStyleClass().add("return-button");

        continueButton.setOnAction(event -> {
            try {
                INSTANCE.setPlayer(new Player(nameField.getText(), INSTANCE.getPlayer().getHealth(),
                        INSTANCE.getPlayer().getScore(), INSTANCE.getPlayer().getGold()));
                createPlayerController.goTo(NewGameView.class).handle(event);
            } catch (Exception e) {
                AlertDialog.showWarning(e.getMessage());
            }
        });
        returnButton.setOnAction(e -> StageManager.getInstance().goBack());

    }
}
