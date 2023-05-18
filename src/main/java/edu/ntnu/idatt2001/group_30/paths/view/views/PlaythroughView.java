package edu.ntnu.idatt2001.group_30.paths.view.views;

import edu.ntnu.idatt2001.group_30.paths.controller.PlaytroughController;
import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.goals.Goal;
import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultButton;
import edu.ntnu.idatt2001.group_30.paths.view.components.common.DefaultText;
import edu.ntnu.idatt2001.group_30.paths.view.components.common.Ref;
import edu.ntnu.idatt2001.group_30.paths.view.components.pop_up.AlertDialog;
import java.net.URL;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

/**
 * The view for the play-through of the game.
 * It is responsible for displaying the current passage, the current goals and the current inventory.
 * It also displays the current statistics for the player, like health, score and gold.
 *
 * @author Nicolai H. Brand
 */
public class PlaythroughView extends View<VBox> {

    private final PlaytroughController controller;

    /**
     * Creates a new instance of the view.
     * It initializes the controller and sets up the view.
     * It also sets up listeners for the game state.
     */
    public PlaythroughView() {
        super(VBox.class);
        controller = new PlaytroughController();

        /*
         * The view is divided into three parts:
         * +------------------------------------------------------+
         * |                         Header                       |
         * +------------------------------------------------------+
         * |                                    |  player & game  |
         * |                                    |       info      |
         * |                                    | --------------- |
         * |          play-trough window        |      goals      |
         * |                                    | --------------- |
         * |                                    |    inventory    |
         * |                                    |                 |
         * +------------------------------------------------------+
         */

        /* header */
        add(headerBox());

        /* main content */
        HBox mainContent = new HBox();
        mainContent.paddingProperty().setValue(new javafx.geometry.Insets(20));
        mainContent.getChildren().add(playtroughBox(mainContent));
        mainContent.getChildren().add(infoBox(mainContent));
        add(mainContent);

        /* game state */
        controller
            .getGameWon()
            .addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    boolean continuing = AlertDialog.showConfirmation(
                        "Congratulations, you completed all your goals and won the game." +
                        "You can still continue to the story if its not finished. Do you want to continue the story?",
                        "You won!"
                    );
                    if (!continuing) controller.goToHome();
                }
            });
        controller
            .getGameOver()
            .addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    AlertDialog.showConfirmation("You lost!", "You lost!");
                    controller.goTo(HomeView.class);
                }
            });
    }

    /**
     * Creates the header box.
     * It contains the title of the game and buttons for navigation.
     * @return The parent node of the header box.
     */
    private Node headerBox() {
        /* container box configuration */
        HBox header = new HBox();
        header.setAlignment(Pos.TOP_CENTER);
        header.setSpacing(20);
        header.paddingProperty().setValue(new javafx.geometry.Insets(20));
        header.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

        /* header content */
        header.getChildren().add(Ref.bigText(controller.getGameTitle()));
        header.getChildren().add(new Separator(Orientation.VERTICAL));
        header.getChildren().add(DefaultButton.medium("Home", controller.goTo(HomeView.class)));
        header
            .getChildren()
            .add(
                DefaultButton.medium(
                    "Restart",
                    e -> {
                        if (
                            AlertDialog.showConfirmation(
                                "Are you sure you want to restart the game? All progress will be lost.",
                                "Confirm restart"
                            )
                        ) controller.startNewGame();
                    }
                )
            );
        header.getChildren().add(DefaultButton.medium("Help", controller.goTo(HelpView.class)));
        return header;
    }

    /**
     * Creates the play-trough box.
     * It contains the current passage and the links to the next passages.
     *
     * @return The parent node of the play-trough box.
     */
    private Node playtroughBox(Pane parentPane) {
        /* container box configuration */
        VBox playtrough = new VBox();
        playtrough.getStyleClass().add("box-shadow");
        playtrough.prefWidthProperty().bind(parentPane.widthProperty().multiply(0.6));
        playtrough.prefHeightProperty().bind(parentPane.heightProperty());
        playtrough.setAlignment(Pos.TOP_CENTER);
        playtrough.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(30), null)));

        /* Passage title */
        playtrough.getChildren().add(Ref.mediumText(controller.getPassageTitle()));
        /* Passage content */
        ScrollPane passagePane = new ScrollPane();
        passagePane.paddingProperty().setValue(new javafx.geometry.Insets(20));
        passagePane.setMinHeight(350);
        Text content = Ref.smallText(controller.getPassageContent());
        content.wrappingWidthProperty().bind(passagePane.widthProperty().multiply(0.85));
        passagePane.setContent(content);
        playtrough.getChildren().add(passagePane);

        /* links */
        ScrollPane linksBoxWrapper = new ScrollPane();
        linksBoxWrapper.prefHeightProperty().bind(parentPane.heightProperty().multiply(0.5));
        linksBoxWrapper.centerShapeProperty().setValue(true);
        VBox linksBox = new VBox();
        linksBox.setAlignment(Pos.TOP_CENTER);
        linksBox.prefWidthProperty().bind(linksBoxWrapper.widthProperty());

        linksBox.setSpacing(10);
        ObservableList<Link> links = controller.getLinks();
        links.addListener(
            (ListChangeListener<Link>) change -> {
                linksBox.getChildren().clear();
                populateLinksBox(linksBox, links);
            }
        );
        populateLinksBox(linksBox, links);
        linksBoxWrapper.setContent(linksBox);
        playtrough.getChildren().add(linksBoxWrapper);

        return playtrough;
    }

    private void populateLinksBox(VBox linksBox, ObservableList<Link> links) {
        linksBox.getChildren().clear();
        for (Link link : links) {
            Button button = DefaultButton.medium(link.getText(), e -> controller.chooseLink(link));
            button.setWrapText(true);
            button.setPrefWidth(300);
            button.getStyleClass().add("link-button");
            linksBox.getChildren().add(button);
        }
    }

    /**
     * Creates the info box.
     * It contains the current player information, like health, score and gold.
     * It also contains the list of all goals.
     *
     * @return The parent node of the statistics box.
     */
    private Node infoBox(Pane parentPane) {
        VBox infoBox = new VBox();
        infoBox.prefWidthProperty().bind(parentPane.widthProperty().multiply(0.4));
        infoBox.prefHeightProperty().bind(parentPane.heightProperty());
        infoBox.setAlignment(Pos.TOP_CENTER);
        infoBox.setSpacing(20);
        infoBox.setPadding(new Insets(0, 0, 0, 20)); // Set 20 pixels of left padding

        /* player information */
        infoBox.getChildren().add(playerInfo(infoBox));
        /* list of all goals */
        infoBox.getChildren().add(goalInfo(infoBox));
        /* inventory */
        infoBox.getChildren().add(inventoryInfo(infoBox));
        return infoBox;
    }

    /**
     * Creates the player information box.
     * It contains the player name, score, health and gold.
     *
     * @return The parent node of the player information box.
     */
    private Node playerInfo(Pane parentPane) {
        /*
         *         Player info:
         * player info     |  image
         */
        VBox playerInfoBox = new VBox();
        playerInfoBox.getStyleClass().add("box-shadow");
        playerInfoBox.prefHeightProperty().bind(parentPane.heightProperty().multiply(0.3));
        playerInfoBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(30), null)));
        playerInfoBox.setAlignment(Pos.TOP_CENTER);
        playerInfoBox.setSpacing(10);

        /* title */
        playerInfoBox.getChildren().add(DefaultText.medium("Player info:"));

        /* player data and image container */
        HBox playerDataAndImage = new HBox();
        playerDataAndImage.setAlignment(Pos.TOP_LEFT);

        /* player data */
        VBox playerData = new VBox();
        playerData.setAlignment(Pos.TOP_CENTER);
        playerData.setSpacing(10);
        playerData.prefWidthProperty().bind(playerInfoBox.widthProperty().multiply(0.5));
        playerData.getChildren().add(DefaultText.small("Name: " + controller.getPlayerName()));

        HBox score = new HBox();
        score.setAlignment(Pos.TOP_CENTER);
        score.getChildren().add(DefaultText.small("Score: "));
        score.getChildren().add(Ref.smallText(controller.getScore()));
        playerData.getChildren().add(score);

        HBox health = new HBox();
        health.setAlignment(Pos.TOP_CENTER);
        health.getChildren().add(DefaultText.small("Health: "));
        health.getChildren().add(Ref.smallText(controller.getHealth()));
        playerData.getChildren().add(health);

        HBox gold = new HBox();
        gold.setAlignment(Pos.TOP_CENTER);
        gold.getChildren().add(DefaultText.small("Gold: "));
        gold.getChildren().add(Ref.smallText(controller.getGold()));
        playerData.getChildren().add(gold);

        /* player image */
        ImageView characterImageView = controller.getCharacterImageView();
        characterImageView.setFitHeight(150);
        characterImageView.setPreserveRatio(true);

        playerDataAndImage.getChildren().add(playerData);
        playerDataAndImage.getChildren().add(characterImageView);

        playerInfoBox.getChildren().add(playerDataAndImage);

        return playerInfoBox;
    }

    /**
     * Creates the goal information box.
     * It contains the list of all goals.
     *
     * @return The parent node of the goal information box.
     */
    private Node goalInfo(Pane parentPane) {
        Pane goalInfoBox = initInfoCard(parentPane);

        /* title */
        goalInfoBox.getChildren().add(DefaultText.medium("Goals:"));

        /* content */
        ScrollPane goalBox = new ScrollPane();
        goalBox.setMinHeight(125);
        VBox content = new VBox();
        content.setAlignment(Pos.TOP_LEFT);
        content.setSpacing(20);
        content.setPadding(new Insets(0, 0, 0, 20));

        ObservableMap<Goal, Boolean> goals = controller.getGoals();
        goals.addListener(
            (MapChangeListener<Goal, Boolean>) change -> {
                showGoals(goals, content);
            }
        );

        showGoals(goals, content);
        goalBox.setContent(content);
        goalInfoBox.getChildren().add(goalBox);
        return goalInfoBox;
    }

    /**
     * Creates the inventory information box.
     * It contains the list of all items in the inventory.
     * @return The parent node of the inventory information box.
     */
    private Node inventoryInfo(Pane parentPane) {
        Pane inventoryInfoBox = initInfoCard(parentPane);

        /* title */
        inventoryInfoBox.getChildren().add(DefaultText.medium("Inventory:"));

        /* content */
        ScrollPane inventoryBox = new ScrollPane();
        inventoryBox.setMinHeight(125);
        VBox content = new VBox();
        content.setAlignment(Pos.TOP_LEFT);
        content.setSpacing(20);

        ObservableList<String> inventory = controller.getInventory();
        inventory.addListener(
            (ListChangeListener<String>) change -> {
                showInventory(inventory, content);
            }
        );

        showInventory(inventory, content);
        inventoryBox.setContent(content);
        inventoryInfoBox.getChildren().add(inventoryBox);
        return inventoryInfoBox;
    }

    private Pane initInfoCard(Pane parentPane) {
        VBox box = new VBox();
        box.getStyleClass().add("box-shadow");
        box.setAlignment(Pos.TOP_CENTER);
        box.prefHeightProperty().bind(parentPane.heightProperty().multiply(0.3));
        box.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(30), null)));
        return box;
    }

    private void showInventory(ObservableList<String> inventory, Pane content) {
        content.getChildren().clear();
        for (String item : inventory) {
            content.getChildren().add(DefaultText.small(item));
        }
    }

    /**
     * Shows the goals in the given content pane.
     *
     * @param goals The goals to show.
     * @param content The pane to show the goals in.
     */
    private void showGoals(ObservableMap<Goal, Boolean> goals, Pane content) {
        content.getChildren().clear();
        goals.forEach((goal, completed) -> {
            HBox goalBox = new HBox();
            goalBox.setAlignment(Pos.TOP_LEFT);
            goalBox.setSpacing(10);
            goalBox.getChildren().add(DefaultText.small(goal.toString()));
            //goalBox.getChildren().add(DefaultText.small(completed ? "Completed" : "Not completed"));
            URL imageUrl = getClass().getResource("/images/checkbox-" + (completed ? "marked" : "blank") + ".png");
            if (imageUrl != null) {
                ImageView imageView = new ImageView(imageUrl.toString());
                imageView.setFitWidth(20);
                imageView.setPreserveRatio(true);
                goalBox.getChildren().add(imageView);
            } else {
                System.err.println("Unable to load image: " + imageUrl);
            }

            content.getChildren().add(goalBox);
        });
    }
}
