package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.PathsSingleton;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class StoryCreation extends Application {
    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 1000;
    private static Stage stage;
    private Point2D contentPaneInitOffset;
    private final ObservableList<PassageNode> passages = FXCollections.observableArrayList();

    /**
     * The entry point of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The start method is called by the JavaFX runtime after the init method has returned.
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        StoryCreation.stage = stage;
        StoryCreation.stage.setTitle("Paths");
        AnchorPane anchorPane = new AnchorPane();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1000, 1000);

        Pane contentPane = new Pane();
        contentPane.setStyle("-fx-border-color: black;");
        scrollPane.setContent(contentPane);


        PassageNode passage1 = new PassageNode(50, 50, 100, 50, new Passage("Passage title", "Content"));
        PassageNode passage2 = new PassageNode(200, 200, 100, 50, new Passage("Passage title2", "Content2"));

        passages.addAll(passage1, passage2);
        //TODO: make class for GUI passage, containing place for GUI link...
        Line link1 = new Line(100, 75, 200, 225);

        contentPane.getChildren().addAll(passage1, passage2, link1);

        addContentPaneEventListeners(contentPane);

        scrollPane.setOnZoom(event -> {
            double zoomFactor = event.getTotalZoomFactor();
            contentPane.setScaleX(contentPane.getScaleX() * zoomFactor);
            contentPane.setScaleY(contentPane.getScaleY() * zoomFactor);
        });

        Button addPassage = new Button("Add Passage");
        VBox menu = new VBox(addPassage);
        menu.setTranslateX(300);
        menu.setTranslateY(300);


        anchorPane.getChildren().addAll(
                scrollPane,
                menu
                );

        addPassage.onActionProperty().set(actionEvent -> {
            PassageNode newPassage = new PassageNode(200 + 100 * passages.size(), 200, 100, 50, new Passage("Passage title", "Content2"));
            passages.add(newPassage);
            contentPane.getChildren().add(newPassage);
        });

        Scene scene = new Scene(anchorPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        StoryCreation.stage.setScene(scene);
        StoryCreation.stage.show();


    }

    private void addContentPaneEventListeners(Pane contentPane) {
        contentPane.setOnMousePressed(mouseEvent -> {
            contentPane.setCursor(Cursor.HAND);
            contentPaneInitOffset = new Point2D(mouseEvent.getX(), mouseEvent.getY())
                    .subtract(contentPane.getTranslateX(), contentPane.getTranslateY());
        });

        contentPane.setOnMouseReleased(mouseEvent -> {
            contentPane.setCursor(Cursor.DEFAULT);
        });

        contentPane.setOnMouseDragged(mouseEvent -> {
            if(!PathsSingleton.INSTANCE.isPassageMoving()) {
                contentPane.setCursor(Cursor.HAND);

                double newX = mouseEvent.getX() - contentPaneInitOffset.getX();
                double newY = mouseEvent.getY() - contentPaneInitOffset.getY();
                contentPane.setTranslateX(newX);
                contentPane.setTranslateY(newY);

            }
        });

        contentPane.setOnMouseClicked(mouseEvent -> {
            PassageNode newPassage = new PassageNode(mouseEvent.getX(), mouseEvent.getY(), 100, 50, new Passage("Passage title", "Content2"));
            passages.add(newPassage);
            contentPane.getChildren().add(newPassage);
        });
    }
}
