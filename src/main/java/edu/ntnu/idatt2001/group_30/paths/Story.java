package edu.ntnu.idatt2001.group_30.paths;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

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
        addPassage(this.openingPassage);
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
     * Attempts to remove a passage from the collection of passages based on the given link.
     * Will only remove the passage if no other passage links to it.
     * @param link the link associated with the passage to remove.
     * @return {@code true} if the passage was removed successfully, {@code false} otherwise.
     */
    public boolean removePassage(Link link) {
        Passage toRemove = this.passages.get(link);
        /* the passage was not found */
        if (toRemove == null)
            return false;


        /* find out if any other passages has a link to the passage we want to remove */
        boolean anyMatch = this.passages.values()
                .stream()
                .flatMap(passage -> passage.getLinks().stream())
                .anyMatch(l -> l.equals(link));

        //TODO: should we throw an exception here instead?
        if (anyMatch)
            return false;

        this.passages.remove(link);
        return true;
    }

    /**
     * Finds all the broken links in the story.
     * A link is considered broken if it does not point to a passage in the stories map of passages.
     * @return a list of all the broken links in the story
     */
    public List<Link> getBrokenLinks() {
        return this.passages.values()
                .stream()
                .flatMap(passage -> passage.getLinks().stream())
                .filter(link -> this.passages.get(link) == null)
                .toList();
    }

    /**
     * @return the title of the Story
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method retrieves all the passages of a story.
     * @return All the passages of the Story as a {@code Collection<Passages>}.
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

        this.passages.values().forEach(passage -> {
            if(!passage.equals(openingPassage)) sb.append(passage.toString()).append("\n");
        });

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Story story)) return false;

        if (!Objects.equals(title, story.title)) return false;
        if (!Objects.equals(passages, story.passages)) return false;
        return Objects.equals(openingPassage, story.openingPassage);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (passages != null ? passages.hashCode() : 0);
        result = 31 * result + (openingPassage != null ? openingPassage.hashCode() : 0);
        return result;
    }
}
