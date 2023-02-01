package edu.ntnu.idatt2001.group_30;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Passage class represents a part of the story. It, therefore, contains information surrounding that part, as well
 * as the different directions the story may take.
 *
 * @author Trym Hamer Gudvangen
 */
public class Passage {
    private final String title;
    private final String content;
    private final List<Link> links;

    /**
     * This constructor creates a passage object, which represents a single point in the story.
     * @param title     The title of the passage, represented using a String.
     * @param content   The content of the passage, represented using a String.
     */
    public Passage(String title, String content) {
        this.title = title;
        this.content = content;
        this.links = new ArrayList<>();
    }

    /**
     * This method retrieves the title of the passage.
     * @return The title of the passage, represented as a String.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * This method retrieves the content of the passage.
     * @return The content of the passage, represented as a String.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * This method adds another link to the passage.
     * @param link  The link to be added, represented using a Link object.
     * @return      Status of adding the link. {@code true} if link was successfully added, otherwise {@code false}.
     */
    public boolean addLink(Link link) {
        boolean successful = false;
        this.links.add(link);
        return successful;
    }

    /**
     * This method retrieves the list of links of the passage.
     * @return The links attached to this passage, given as a List{@code <Link>}.
     */
    public List<Link> getLinks() {
        return this.links;
    }

    /**
     * This method checks whether the passage has any links in its links list.
     * @return Status of links list. {@code true} if there are links in the list, else {@code false}.
     */
    public boolean hasLinks() {
        return this.links.size() > 0;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", links=" + links +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passage passage)) return false;

        if (!Objects.equals(title, passage.title)) return false;
        if (!Objects.equals(content, passage.content)) return false;
        return Objects.equals(links, passage.links);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (links != null ? links.hashCode() : 0);
        return result;
    }
}
