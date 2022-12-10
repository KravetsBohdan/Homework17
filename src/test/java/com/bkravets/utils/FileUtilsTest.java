package com.bkravets.utils;


import com.bkravets.models.Person;
import com.bkravets.models.Sex;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FileUtilsTest {

    @AfterAll
    static void afterAll() throws IOException {
        Files.deleteIfExists(Path.of("image.jpg"));
        Files.deleteIfExists(Path.of("out", "result.csv"));
        Files.deleteIfExists(Path.of("out"));
    }

    @Test
    void shouldDownloadPicture() {
        FileUtils.downloadPicture(
                "https://www.nasa.gov/sites/default/files/thumbnails/image/" +
                        "main_image_star-forming_region_carina_nircam_final-5mb.jpg",
                "image.jpg");

        Path expectedPath = Path.of("image.jpg");

        assertThatPath(expectedPath).exists().hasExtension("jpg");
    }


    @Test
    void shouldWritePeopleToCsv() {
        List<Person> people = List.of(
                new Person("Ned", 55, Sex.MAN),
                new Person("Ket", 22, Sex.WOMAN));

        String expectedContent = """
                Ned,55,MAN
                Ket,22,WOMAN
                """;

        FileUtils.writePeopleToCsv(people);

        Path expectedPath = Path.of("out", "result.csv");
        assertThatPath(expectedPath)
                .exists()
                .content()
                .containsIgnoringWhitespaces(expectedContent);
    }
}