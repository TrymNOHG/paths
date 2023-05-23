package edu.ntnu.idatt2001.group_30.paths.view.components;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This class represents a component for displaying a carousel of images.
 *
 * @author Trym Hamer Gudvangen
 */
public class ImageCarousel {

    private final LinkedList<Image> images = new LinkedList<>();
    private int currentIndex;
    private ImageView currentImage = new ImageView();
    private int size;
    private final int WIDTH = 150;
    private final int HEIGHT = 150;

    /**
     * Constructor for the ImageCarousel component.
     * @param imageNames    A list of image names, as Strings.
     */
    public ImageCarousel(List<String> imageNames) {
        if (imageNames == null || imageNames.isEmpty()) {
            throw new IllegalArgumentException("Image URI list must not be empty.");
        }

        for (String imageURI : imageNames) {
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

    /**
     * Method for getting the carousel component.
     * @return  The carousel component, as an HBox.
     */
    public HBox getCarousel() {
        Button leftButton = new Button("<");
        leftButton.setOnAction(e -> previous());

        Button rightButton = new Button(">");
        rightButton.setOnAction(e -> next());

        HBox carousel = new HBox(leftButton, currentImage, rightButton);
        carousel.setAlignment(Pos.CENTER);
        return carousel;
    }

    /**
     * Method for getting the next image.
     */
    public void next() {
        currentIndex = (currentIndex + 1) % size;
        currentImage.setImage(images.get(currentIndex));
    }

    /**
     * Method for getting the previous image.
     */
    public void previous() {
        currentIndex = (currentIndex - 1 + size) % size;
        currentImage.setImage(images.get(currentIndex));
    }

    /**
     * Method for getting the size of the list of images.
     * @return  The size of the list of images, as an int.
     */
    public int size() {
        return size;
    }

    /**
     * Method for getting the current image.
     * @return  The current image, as an ImageView.
     */
    public ImageView getCurrentImage() {
        return currentImage;
    }
}
