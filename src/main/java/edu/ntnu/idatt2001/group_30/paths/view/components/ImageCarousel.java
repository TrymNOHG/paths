package edu.ntnu.idatt2001.group_30.paths.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

//TODO: make this into an HBOX with arrows that actually work.
// Add option of looping or not and adjust how arrows are display accordingly
public class ImageCarousel {

    private final LinkedList<Image> images = new LinkedList<>();
    private int currentIndex;
    private ImageView currentImage = new ImageView();
    private int size;
    private final int WIDTH = 150;
    private final int HEIGHT = 150;

    public ImageCarousel(List<String> imageNames) {
        if (imageNames == null || imageNames.isEmpty()) {
            throw new IllegalArgumentException("Image URI list must not be empty.");
        }

        for(String imageURI : imageNames) {
            URL imageUrl = getClass().getResource(imageURI);
            if (imageUrl != null) {
                images.add(new Image(imageUrl.toString()));
            } else {
                System.err.println("Unable to load image: " + imageURI);
            }
        }

        this.currentIndex = 0;
        this.size = images.size();
        this.currentImage.setFitWidth(WIDTH);
        this.currentImage.setFitHeight(HEIGHT);
        this.currentImage.setImage(images.getFirst());
    }

    public HBox getCarousel() {
        Button leftButton = new Button("<");
        leftButton.setOnAction(e -> previous());

        Button rightButton = new Button(">");
        rightButton.setOnAction(e -> next());

        HBox carousel = new HBox(leftButton, currentImage, rightButton);
        carousel.setAlignment(Pos.CENTER);

        return carousel;
    }

    public void next() {
        currentIndex = (currentIndex + 1) % size;
        currentImage.setImage(images.get(currentIndex));
    }

    public void previous() {
        currentIndex = (currentIndex - 1 + size) % size;
        currentImage.setImage(images.get(currentIndex));
    }

    public int size() {
        return size;
    }
}
