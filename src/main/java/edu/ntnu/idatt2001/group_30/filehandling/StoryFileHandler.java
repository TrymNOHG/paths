package edu.ntnu.idatt2001.group_30.filehandling;

import edu.ntnu.idatt2001.group_30.Link;
import edu.ntnu.idatt2001.group_30.Passage;
import edu.ntnu.idatt2001.group_30.Story;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class maintains the storage and retrieval of a Story file. This is done through a Buffered
 * writer and reader.
 */
public class StoryFileHandler {

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
        File file = FileHandler.createFile(fileName);
        if(FileHandler.fileExists(file)) throw new IllegalArgumentException("You cannot overwrite a pre-existing story file");
        try(BufferedWriter storyBufferedWriter = new BufferedWriter(new FileWriter(file))){
            storyBufferedWriter.write(story.toString());
        }
    }

    /**
     * This method takes a story file and parses it to create a story object.
     * @param fileName      The name of the story file, given as a String.
     * @return              The story from the file, given as a Story object.
     * @throws IOException  This exception is thrown if an I/O error occurs with the reader.
     */
    public Story readStoryFromFile(String fileName) throws IOException {
        File file = new File(FileHandler.getFileSourcePath(fileName));
        if(!FileHandler.fileExists(file)) throw new IllegalArgumentException("There is no story file with that name!");
        Story story;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            String storyTitle = bufferedReader.readLine();
            bufferedReader.readLine();

            List<String> passageInfo = new ArrayList<>(List.of(bufferedReader.lines()
                    .collect(Collectors.joining("\n")).split("::")));
            passageInfo.remove(0);
            Passage openingPassage = parseStringToPassage(passageInfo.remove(0));

            story = new Story(storyTitle, openingPassage);

            passageInfo.forEach(passage -> story.addPassage(parseStringToPassage(passage)));
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
    private Passage parseStringToPassage(String passageInfo) {
        String[] splitPassageInfo = passageInfo.split("\n");
        Passage passage = new Passage(splitPassageInfo[0], splitPassageInfo[1]);
        for(int i = 2; i < splitPassageInfo.length; i++) {
            passage.addLink(parseStringToLink(splitPassageInfo[i]));
        }

        return passage;
    }

    /**
     * This method takes a String containing the information that is vital for a Link. It, then, parses the
     * string into the text and reference of the Link.
     * @param linkInfo  The information of the link, given as a String.
     * @return          The link, given as a Link object.
     */
    private Link parseStringToLink(String linkInfo){
        String text = linkInfo.substring(linkInfo.indexOf("["), linkInfo.indexOf("]") + 1);
        String reference = linkInfo.substring(linkInfo.indexOf("("), linkInfo.indexOf(")") + 1);

        return new Link(text, reference);
    }

}