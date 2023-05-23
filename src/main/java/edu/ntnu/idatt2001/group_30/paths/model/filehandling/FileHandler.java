package edu.ntnu.idatt2001.group_30.paths.model.filehandling;

import edu.ntnu.idatt2001.group_30.paths.exceptions.InvalidExtensionException;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains general-use methods for handling files such as file creation and name and path validation.
 *
 * @author Trym Hamer Gudvangen, Nicolai H. Brand.
 */
public class FileHandler {

    private static final Pattern VALID_CHAR = Pattern.compile("[^<>:\"/*|?\\\\]*");
    private static final Pattern VALID_EXTENSION = Pattern.compile(".*\\.paths$");
    private static final String DEFAULT_PATH =
        "src" + File.separator + "main" + File.separator + "resources" + File.separator + "story-files";
    private static Path defaultPath = Paths.get(DEFAULT_PATH);

    /**
     * This method checks whether the given file name is valid.
     * @param fileName                  Name of the given file, given as a String.
     * @return                          {@code true} if the file name is valid.
     * @throws IllegalArgumentException This exception is thrown if given file name is blank or has invalid characters.
     */
    public static boolean isFileNameValid(String fileName) throws IllegalArgumentException {
        if (fileName.isBlank()) throw new IllegalArgumentException("File name cannot be blank");
        Matcher matcher = VALID_CHAR.matcher(fileName);
        if (!matcher.matches()) {
            String illegalChars = findInvalidCharacters(fileName);
            throw new IllegalArgumentException("File name contains invalid characters: " + illegalChars);
        }
        return true;
    }

    /**
     * This is a helper method for finding the specific invalid characters in a file name.
     * This is used to give a more specific error message to the user.
     * @param fileName Name of the file, given as a String
     * @return         A String containing the invalid characters in the file name
     */
    private static String findInvalidCharacters(String fileName) {
        StringBuilder nonMatchingChars = new StringBuilder();
        Set<Character> uniqueChars = new HashSet<>();

        Matcher matcher = VALID_CHAR.matcher(fileName);
        /* finds all the non-matching segments in the file name and adds the unique characters to a set */
        matcher
            .results()
            .forEach(result -> {
                String nonMatchingSegment = fileName.substring(result.start(), result.end());
                nonMatchingSegment
                    .chars()
                    .mapToObj(c -> (char) c)
                    .filter(uniqueChars::add)
                    .forEach(nonMatchingChars::append);
            });

        return nonMatchingChars.toString();
    }

    /**
     * This method checks whether the given file contains .paths as the extension.
     * @param fileName                   Name of the file including extension, given as a String
     * @return                           {@code true} if file name contains .paths, else {@code false}
     * @throws InvalidExtensionException This exception is thrown if the file name does not contain .paths at the end
     */
    public static boolean isFileExtensionValid(String fileName) throws InvalidExtensionException {
        Matcher matcher = VALID_EXTENSION.matcher(fileName);
        if (!matcher.matches()) throw new InvalidExtensionException("File name contains invalid characters");
        return true;
    }

    /**
     * This method checks if a file exists with a given directory path and whether it contains any information.
     * @param file The file to be checked, given as a File object
     * @return     If the file contains no information, {@code false} is returned. Else, {@code true} is returned
     */
    public static boolean fileExists(File file) {
        return file.exists() && file.length() > 0;
    }

    /**
     * This method checks if a file exists with a given directory path and whether it contains any information.
     * @param fileName The file name to be checked, given as a String
     * @return     If the file contains no information, {@code false} is returned. Else, {@code true} is returned
     */
    public static boolean fileExists(String fileName) {
        File file = createFile(fileName);
        return fileExists(file);
    }

    /**
     * This method takes a file name. It, then, checks whether the name is valid and if so, it creates a file for it.
     * @param fileName                      Name of the file, given as a String.
     * @return                              The file with the given name, represented using a File object.
     * @throws IllegalArgumentException     This exception is thrown if the file name is invalid.
     */
    public static File createFile(String fileName) throws IllegalArgumentException {
        isFileNameValid(fileName);
        return new File(getFileSourcePath(fileName));
    }

    /**
     * This method retrieves the file source path of a story file with the file name given.
     * @param fileName Name of the desired file, represented as a String
     * @return         The source path to the file, represented as a String
     */
    public static String getFileSourcePath(String fileName) {
        return defaultPath.resolve(fileName + ".paths").toString();
    }

    /**
     * This method changes the default path used to create files. This may, for example, be used for testing purposes.
     * @param newPath New default path, given as a String
     */
    public static void changeDefaultPath(Path newPath) {
        defaultPath = newPath;
    }
}
