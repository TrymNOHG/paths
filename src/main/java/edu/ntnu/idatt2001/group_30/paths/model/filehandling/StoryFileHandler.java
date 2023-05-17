package edu.ntnu.idatt2001.group_30.paths.model.filehandling;

import edu.ntnu.idatt2001.group_30.paths.exceptions.CorruptFileException;
import edu.ntnu.idatt2001.group_30.paths.exceptions.CorruptLinkException;
import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.actions.Action;
import edu.ntnu.idatt2001.group_30.paths.model.actions.ActionFactory;
import edu.ntnu.idatt2001.group_30.paths.model.actions.ActionType;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class maintains the storage and retrieval of a Story file. This is done through a Buffered
 * writer and reader.
 */
public class StoryFileHandler {

    private final Pattern LINK_PATTERN = Pattern.compile("\\[.*]\\(.*\\)");
    private final Pattern ACTION_PATTERN = Pattern.compile("<.*>\\\\.*/");

    /**
     * This method takes a story and writes its contents to a .paths file. The story information is transcribed
     * in the given format:
     * <pre>
     *  Story title
     *
     *  ::Opening Passage Title
     *  Opening Passage Content
     *  [Link Text](Link Reference)
     *
     *  ::Another Passage Title
     *  Passage Content
     *  [Link Text](Link Reference)
     *  {@code <Action Type>}\Action Value/
     *  [Link Text](Link Reference)
     *
     *  ...
     * </pre>
     * @param story         The story to be saved, given as a Story object.
     * @param fileName      The name of the file the story will be saved to, given as a String.
     * @throws IOException  This exception is thrown if an I/O error occurs with the writer.
     */
    public void createStoryFile(Story story, String fileName) throws IOException {
        Objects.requireNonNull(story, "Story cannot be null");
        Objects.requireNonNull(fileName, "File name cannot be null");
        File file = FileHandler.createFile(fileName);
        if (FileHandler.fileExists(file)) throw new IllegalArgumentException(
            "You cannot overwrite a pre-existing story file"
        );
        try (BufferedWriter storyBufferedWriter = new BufferedWriter(new FileWriter(file))) {
            storyBufferedWriter.write(story.toString());
        }
    }

    /**
     * This method takes a story file and parses it to create a story object.
     * @param fileName      The name of the story file, given as a String.
     * @return              The story from the file, given as a Story object.
     * @throws IOException  This exception is thrown if an I/O error occurs with the reader.
     */
    public Story readStoryFromFile(String fileName) throws IOException, InstantiationException {
        Objects.requireNonNull(fileName, "File name cannot be null");
        File file = new File(FileHandler.getFileSourcePath(fileName));
        return readStoryFromFile(file);
    }

    //TODO: test new readStory method... basically same as earlier

    /**
     * This method takes a story file and parses it to create a story object.
     * @param file      The story file, given as a File object.
     * @return              The story from the file, given as a Story object.
     * @throws IOException  This exception is thrown if an I/O error occurs with the reader.
     */
    public Story readStoryFromFile(File file) throws IOException, InstantiationException {
        Objects.requireNonNull(file, "File does not exist");
        if (!FileHandler.fileExists(file)) throw new IllegalArgumentException("There is no story file with that name!");
        Story story;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String storyTitle = bufferedReader.readLine();

            List<String> passageInfo = new ArrayList<>(
                List.of(
                    bufferedReader
                        .lines()
                        .collect(Collectors.joining(System.lineSeparator()))
                        .split(System.lineSeparator() + "::")
                )
            );

            if (passageInfo.size() == 1) throw new CorruptFileException(
                "The title of a passage was corrupt.\nPassage title: " +
                storyTitle +
                "\nThe expected format is ::PASSAGE TITLE NAME"
            );
            passageInfo.remove(0);

            Passage openingPassage = parseStringToPassage(passageInfo.remove(0));

            story = new Story(storyTitle, openingPassage);

            for (String passage : passageInfo) {
                story.addPassage(parseStringToPassage(passage));
            }
        }

        return story;
    }

    /**
     * This method takes a String containing the information that is vital for a Passage. It, then, parses the
     * string into the title, content, and links of the passage. The Links are added to the passage through the
     * {@link Passage#addLink(Link)} method.
     * @param passageInfo   Info of the passage, given as a String.
     * @return              The passage, given as a Passage object.
     */
    private Passage parseStringToPassage(String passageInfo) throws InstantiationException {
        String[] splitPassageInfo = passageInfo.split(System.lineSeparator());
        Passage passage = new Passage(splitPassageInfo[0], splitPassageInfo[1]);
        for (int i = 2; i < splitPassageInfo.length; i++) {
            Link link = parseStringToLink(splitPassageInfo[i]);
            passage.addLink(link);
            while (i + 1 < splitPassageInfo.length && ACTION_PATTERN.matcher(splitPassageInfo[i + 1]).matches()) {
                link.addAction(parseStringToAction(splitPassageInfo[++i]));
            }
        }

        return passage;
    }

    /**
     * This method takes a String containing the information that is vital for a Link. It, then, parses the
     * string into the text and reference of the Link.
     * @param linkInfo  The information of the link, given as a String.
     * @return          The link, given as a Link object.
     */
    private Link parseStringToLink(String linkInfo) {
        if (!LINK_PATTERN.matcher(linkInfo).matches()) throw new CorruptLinkException(
            "The link info is corrupt in the file.\n" +
            "Link info: " +
            linkInfo +
            "\nThe link should be in this form [Link Name](Reference)"
        );
        String text = linkInfo.substring(linkInfo.indexOf("[") + 1, linkInfo.indexOf("]"));
        String reference = linkInfo.substring(linkInfo.indexOf("(") + 1, linkInfo.indexOf(")"));

        return new Link(text, reference);
    }

    /**
     * This method takes a String containing the information that is vital for an Action. It, then, parses the
     * string into the implementation and value.
     * @param actionInfo  The information of the Action, given as a String.
     * @return          The action implementation, given as an Action object.
     */
    private Action<?> parseStringToAction(String actionInfo) throws InstantiationException {
        String className = actionInfo.substring(actionInfo.indexOf("<") + 1, actionInfo.indexOf(">"));
        String value = actionInfo.substring(actionInfo.indexOf("\\") + 1, actionInfo.indexOf("/"));

        ActionType actionType = extractActionTypeFromInfo(className);
        return ActionFactory.getAction(actionType, value);
    }

    /**
     * This method takes in the Action Class info, given as a String. This method checks what ActionType the information
     * belongs to.
     * @param actionTypeInfo          Information of an action's class from a paths File, represented as a String
     * @return                        The type of Action extracted from the String, given as a ActionType enumeration
     * @throws InstantiationException This exception is thrown if the action type information is corrupt
     */
    private ActionType extractActionTypeFromInfo(String actionTypeInfo) throws InstantiationException {
        return switch (actionTypeInfo) {
            case "GoldAction" -> ActionType.GOLD_ACTION;
            case "HealthAction" -> ActionType.HEALTH_ACTION;
            case "InventoryAction" -> ActionType.INVENTORY_ACTION;
            case "ScoreAction" -> ActionType.SCORE_ACTION;
            default -> throw new InstantiationException("The Action type information is corrupt");
        };
    }
}
