package edu.ntnu.idatt2001.group_30;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Link class. A Link makes it possible to move from one Passage to another.
 * Links bind the different parts of a Story together
 *
 * @author Nicolai H. Brand, Trym Hamer Gudvangen
 */
public class Link {
    private final String text;
    private final String reference;
    private final List<Action> actions;

    /**
     * This constructor creates a Link object, which contains information surrounding a linking point in the story.
     * @param text, a describing text that indicates a choice or action in a story
     * @param reference, a string that unambiguously identify a passage
     * @param actions, a list of special objects that determine the behavior of a player
     */
    public Link(String text, String reference) {
        this.text = text;
        this.reference = reference;
        this.actions = new ArrayList<Action>();
    }


    /**
     * Adds an action to the list of actions
     * @param action, the action to be added to the list
     */
    public addAction(Action action) {
        this.actions.add(action);
    }

    /**
     * This method retrieves the text of a link.
     * @return  The text of the link, given as a String.
     */
    public String getText() {
        return this.text;
    }

    /**
     * This method retrieves the reference of the link.
     * @return  The reference of the link object, represented as a String.
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * This method retrieves the list of actions attached to the Link object.
     * @return  The actions of the Link object, given as a List{@code <Action>}.
     */
    public List<Action> getActions() {
        return this.actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(text, link.text) && Objects.equals(reference, link.reference) && Objects.equals(actions, link.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, reference, actions);
    }

    @Override
    public String toString() {
        return "Link{" +
                "text='" + text + '\'' +
                ", reference='" + reference + '\'' +
                ", actions=" + actions +
                '}';
    }
}