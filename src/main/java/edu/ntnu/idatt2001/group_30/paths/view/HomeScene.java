package edu.ntnu.idatt2001.group_30.paths.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HomeScene extends AbstractScene {

    public HomeScene() {
        super("Home");
        this.setRoot(hello());
        //this.addNode(testButton());
    }

    private Pane hello() {
        Pane pane = new Pane();
        Text title = new Text();
        title.setText("Hello World!");
        title.setX(100);
        title.setY(100);
        pane.getChildren().add(title);
        return pane;
    }

    private VBox testButton() {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Button("Hello World!"));
        return vBox;
    }
}
