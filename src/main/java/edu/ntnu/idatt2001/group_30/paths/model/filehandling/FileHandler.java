package edu.ntnu.idatt2001.group_30.paths.model.filehandling;

import edu.ntnu.idatt2001.group_30.paths.exceptions.InvalidExtensionException;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains general-use methods for handling files such as file creation and name and path validation.
 *
 * @author Trym Hamer Gudvangen
 */
public class FileHandler {

    private static final Pattern VALID_CHAR = Pattern.compile("[^<>:\"/*|?\\\\]*");
    private static final Pattern VALID_EXTENSION = Pattern.compile(".*\\.paths$");
    private static String defaultPath = "src/main/resources/story-files";

    /**
     * This method checks whether the given file name is valid.
     * @param fileName                  Name of the given file, given as a String.
     * @return                          {@code true} if the file name is valid.
     * @throws IllegalArgumentException This exception is thrown if given file name is blank or has invalid characters.
     */
    public static boolean isFileNameValid(String fileName) throws IllegalArgumentException {
        if (fileName.isBlank()) throw new IllegalArgumentException("File name cannot be blank");
        Matcher matcher = VALID_CHAR.matcher(fileName);
        if (!matcher.matches()) throw new IllegalArgumentException("File name contains invalid characters");
        return true;
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
        return file.length() > 0;
    }

    /**
     * This method checks if a file exists with a given directory path and whether it contains any information.
     * @param fileName The file name to be checked, given as a String
     * @return     If the file contains no information, {@code false} is returned. Else, {@code true} is returned
     */
    public static boolean fileExists(String fileName) {
        File file = createFile(fileName);
        return file.length() > 0;
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
        return FileSystems.getDefault().getPath(defaultPath, fileName) + ".paths";
    }

    //TODO: test for different OS

    /**
     * This method changes the default path used to create files. This may, for example, be used for testing purposes.
     * @param newPath New default path, given as a String
     */
    public static void changeDefaultPath(String newPath) {
        defaultPath = newPath;
    }
}
