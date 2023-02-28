package edu.ntnu.idatt2001.group_30;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A Story is a non-linear narrative consisting of a collection of passages
 *
 * @author Nicolai H. Brand, Trym Hamer Gudvangen
 */
public class Story {
    private final String title;
    private final Map<Link, Passage> passages;
    private final Passage openingPassage;

    /**
     * Constructor for Story
     *
     * @param title                         the title of the story
     * @param openingPassage                the first passage of the story. Also added to the passage.
     * @throws IllegalArgumentException     This exception is thrown if title or openingPassage is invalid
     */
    public Story(String title, Passage openingPassage) throws IllegalArgumentException{
        //if (title.isBlank() || !title.matches("[a-zA-Z]")) {
        //    throw new IllegalArgumentException("Title cannot be blank, empty, or contain special characters.");
        //}
        if (title.isBlank()) throw new IllegalArgumentException("Title cannot be blank, empty, or contain special characters.");
        this.title = title;
        if (openingPassage == null) throw new IllegalArgumentException("Opening passage cannot be null");
        this.openingPassage = openingPassage;
        this.passages = new HashMap<>();
    }

    /**
     * Adds a passage to the passages map
     * The link object used as the key in the map is made from properties of the passage
     *
     * @param passage, the passage to be added to the passages map
     */
    public void addPassage(Passage passage) {
        Link link = new Link(passage.getTitle(), passage.getTitle());
        this.passages.put(link, passage);
    }

    /**
     * Gets the passage with the given link
     * @param link, the key to the map of passages
     * @return the passage stored with the value link
     */
    public Passage getPassage(Link link) {
        return this.passages.get(link);
    }

    /**
     * @return the title of the Story
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method retrieves all the passages of a story.
     * @return All the pages of the Story as a {@code Collection<Passages>}.
     */
    public Collection<Passage> getPassages() {
        return this.passages.values();
    }

    /**
     * @return the opening passage of the Story
     */
    public Passage getOpeningPassage() {
        return openingPassage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.title).append("\n\n");
        sb.append(this.openingPassage.toString()).append("\n");

        this.passages.values().forEach(passage -> sb.append(passage.toString()).append("\n"));

        return sb.toString();
    }
}
