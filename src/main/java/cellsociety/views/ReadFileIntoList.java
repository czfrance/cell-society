package cellsociety.views;

import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
/**
 * Reads a text file and puts its contents into a list object.
 * Code taken from https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
 */
public class ReadFileIntoList {
    public static List<String> readFileInList(String fileName) {

        List<String> lines = Collections.emptyList();
        try {
            lines =
                    Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {

            // do something
            e.printStackTrace();
        }
        return lines;
    }
}
