package edu.ntnu.idatt2001.group_30.paths.model.filehandling;

import edu.ntnu.idatt2001.group_30.paths.model.Link;
import edu.ntnu.idatt2001.group_30.paths.model.Passage;
import edu.ntnu.idatt2001.group_30.paths.model.Story;
import edu.ntnu.idatt2001.group_30.paths.model.actions.Action;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

/**
 * This class is responsible for writing a story to a file.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand.
 */
public class StoryFileWriter {

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
     *  </pre>
     *
     * @param story    The story to be written to the file.
     * @param fileName The name of the file to be created.
     * @throws IOException if an I/O error occurs with the writer, or if the file already exists.
     */
    public void create(Story story, String fileName) throws IOException {
        Objects.requireNonNull(story, "Story cannot be null");
        Objects.requireNonNull(fileName, "File name cannot be null");

        File file = FileHandler.createFile(fileName);

        create(story, file);
    }

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
     *  </pre>
     *
     * @param story         The story to be written to the file.
     * @param file          The file the story is going to be written to.
     * @throws IOException  if an I/O error occurs with the writer, or if the file already exists.
     */
    public void create(Story story, File file) throws IOException {
        Objects.requireNonNull(story, "Story cannot be null");
        Objects.requireNonNull(file, "File cannot be null");

        if (FileHandler.fileExists(file)) throw new FileAlreadyExistsException(
                "You cannot overwrite a pre-existing story file"
        );

        /* propagate any errors while writing */
        writeStory(story, file);
    }


    //TODO: add test for story files...

    /**
     * Writes the story to the given file.
     * @param story The story to be written to the file.
     * @param file The file to be written to.
     * @throws IOException if an I/O error occurs with the writer.
     */
    private void writeStory(Story story, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write(story.getTitle());
            writer.newLine();
            writer.newLine();

            /* write all passages to the file */
            List<Passage> passages = new ArrayList<>(story.getPassages());
            for (Passage passage : passages) {
                writePassage(passage, writer);
            }
        }
    }

    /**
     * Writes a passage to the file.
     * @param passage The passage to be written to the file.
     * @param writer The writer to be used.
     * @throws IOException if an I/O error occurs with the writer.
     */
    private void writePassage(Passage passage, BufferedWriter writer) throws IOException {
        writer.write("::" + passage.getTitle());
        writer.newLine();
        writer.write(passage.getContent());
        writer.newLine();

        for (Link link : passage.getLinks()) {
            writeLink(link, writer);
        }
        writer.newLine();
    }

    /**
     * Writes a link to the file.
     * @param link  The link to be written to the file.
     * @param writer The writer to be used.
     * @throws IOException if an I/O error occurs with the writer.
     */
    private void writeLink(Link link, BufferedWriter writer) throws IOException {
        writer.write("[");
        writer.write(link.getText());
        writer.write("](");
        writer.write(link.getReference());
        writer.write(")");
        writer.newLine();

        for (Action<?> action : link.getActions()) {
            writeAction(action, writer);
        }
    }

    /**
     * Writes an action to the file.
     * @param action The action to be written to the file.
     * @param writer The writer to be used.
     * @throws IOException  if an I/O error occurs with the writer.
     */
    private void writeAction(Action<?> action, BufferedWriter writer) throws IOException {
        writer.write("<");
        writer.write(action.getClass().getSimpleName());
        writer.write(">\\");
        writer.write(action.getActionValue().toString());
        writer.write("/");
        writer.newLine();
    }
}
