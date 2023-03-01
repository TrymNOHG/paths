package edu.ntnu.idatt2001.group_30.filehandling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.nio.file.FileSystems;

class FileHandlerTest {

    @Nested
    public class The_FileHandler_makes_sure_a_file_name {

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = {"$123test", "50%Off", "***Story***", "LOTR?", "Winnie the Pooh!",
                "LOTR > Hobbit", "The/Hobbit", "[LOTF]", "{LOTR}", "Trym's : Adventure", "Story.paths"})
        void does_not_contain_special_characters(String fileName) {
            String expectedExceptionMessage = "File name contains invalid characters";

            try {
                FileHandler.isFileNameValid(fileName);
            } catch (IllegalArgumentException e) {
                Assertions.assertEquals(expectedExceptionMessage, e.getMessage());
            }
        }

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = {"", "  "})
        void is_not_empty_or_blank(String fileName){
            String expectedExceptionMessage = "File name cannot be blank";

            try {
                FileHandler.isFileNameValid(fileName);
            } catch (IllegalArgumentException e) {
                Assertions.assertEquals(expectedExceptionMessage, e.getMessage());
            }
        }

        @ParameterizedTest(name = "{index}. File name: {0}")
        @ValueSource(strings = {"Winnie the Pooh", "L.O.T.R", "The-Bible", "Story123"})
        void only_contains_valid_characters(String fileName) {
            boolean expectedStatus = true;

            boolean actualStatusOfFile = FileHandler.isFileNameValid(fileName);

            Assertions.assertEquals(expectedStatus, actualStatusOfFile);
        }
    }

    @Nested
    public class The_FileHandler_can_check {
        @Test
        void if_a_file_exists(){
            boolean expectedStatus = true;
            File validFile = new File(FileSystems.getDefault()
                    .getPath("src", "test", "resources", "storytestfiles", "Bones") + ".paths");

            boolean actualStatusOfFile = FileHandler.fileExists(validFile);

            Assertions.assertEquals(expectedStatus, actualStatusOfFile);
        }

        @Test
        void if_a_file_does_not_exist(){
            boolean expectedStatus = false;
            File validFile = new File(FileSystems.getDefault()
                    .getPath("src", "test", "resources", "storytestfiles", "Fairy tale") + ".paths");

            boolean actualStatusOfFile = FileHandler.fileExists(validFile);

            Assertions.assertEquals(expectedStatus, actualStatusOfFile);
        }

        @Test
        void the_file_source_path(){
            String expectedFileSourcePath = "src/main/resources/story-files/story.paths";
            String fileName = "story";

            String actualFileSourcePath = FileHandler.getFileSourcePath(fileName);

            Assertions.assertEquals(expectedFileSourcePath, actualFileSourcePath);
        }
    }

}