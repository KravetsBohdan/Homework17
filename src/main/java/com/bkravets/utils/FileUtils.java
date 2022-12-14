package com.bkravets.utils;

import com.bkravets.models.Person;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtils {

    public static void downloadPicture(String url, String fileName) {
        Path path = Path.of(fileName);

        try (InputStream inputStream = new URL(url).openStream()) {
            Files.deleteIfExists(path);
            Files.copy(inputStream, path);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void writePeopleToCsv(List<Person> people) {
        List<String> csvRepresentation = people.stream()
                .map(person -> String.format("%s,%d,%s", person.name(), person.age(), person.sex()))
                .toList();

        Path directory = Path.of("out");
        Path file = Path.of("out", "result.csv");


        try {
            Files.deleteIfExists(file);
            Files.deleteIfExists(directory);

            Files.createDirectory(directory);
            Files.write(file, csvRepresentation);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
