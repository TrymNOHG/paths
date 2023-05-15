package edu.ntnu.idatt2001.group_30.paths.view;

import edu.ntnu.idatt2001.group_30.paths.PathsSingleton;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/*
    TODO:
      - add ability to edit.
      - add links (double click to draw? click again on another passage to add link?)
      - problems: reconstructing the layout of shapes (need to store positions)
 */

public class PassageNode extends Pane {

    private final Passage passage;
    private final Rectangle rectangle;
    private final Text text;
    private Point2D passageInitOffset;

    private final List<Line> links = new ArrayList<>(); //TODO: may need to actually make a linkNode class...

    public PassageNode(Passage passage) {
        this(100, 100, 100, 100, passage);
    }

    public PassageNode(double v, double v1, Passage passage) {
        this(v, v1, 100, 100, passage);
    }

    public PassageNode(double v, double v1, Paint paint, Passage passage) {
        this(v, v1, 100, 100, passage);
        this.rectangle.setFill(paint);
    }

    public PassageNode(double v, double v1, double v2, double v3, Passage passage) {
        this.passage = passage;
        this.rectangle = new Rectangle(0, 0, v2, v3);
        this.rectangle.setFill(Color.WHITE);
        this.rectangle.setStyle("-fx-border-color: black;");
        this.text = new Text(passage.toString());
        this.text.setFill(Color.BLACK);

        addPassageEventListener();

        getChildren().addAll(this.rectangle, this.text);
        this.setLayoutX(v);
        this.setLayoutY(v1);
        this.setPrefSize(v2, v3);
        //        this.setTranslateX(v);
        //        this.setTranslateY(v1);

    }

    private void addPassageEventListener() {
        this.setOnMousePressed(mouseEvent -> {
                this.setCursor(Cursor.HAND);
                this.passageInitOffset =
                    new Point2D(mouseEvent.getX(), mouseEvent.getY())
                        .subtract(this.getTranslateX(), this.getTranslateY());
            });

        this.setOnMouseDragged(event -> {
                this.setCursor(Cursor.MOVE);
                PathsSingleton.INSTANCE.setPassageMoving(true);

                double newX = event.getX() - this.passageInitOffset.getX();
                double newY = event.getY() - this.passageInitOffset.getY();

                this.setTranslateX(newX);
                this.setTranslateY(newY);
                //            this.getChildren().forEach(child -> child.setTranslateX(event.getX() - this.getWidth()/2));
                //            this.setX(event.getX() - this.getWidth()/2);
                //            this.setY(event.getY() - this.getHeight()/2);
                //TODO: adjust link as well
            });

        this.setOnMouseReleased(mouseDragEvent -> {
                PathsSingleton.INSTANCE.setPassageMoving(false);
            });
    }
}
