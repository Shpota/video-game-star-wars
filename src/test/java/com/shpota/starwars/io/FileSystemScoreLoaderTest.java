package com.shpota.starwars.io;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class FileSystemScoreLoaderTest {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    void shouldInitialize() throws IOException {
        tempFolder.create();
        String filePath = tempFolder.getRoot().getPath() + "/" + UUID.randomUUID() + ".txt";

        FileSystemScoreLoader.initialize(filePath);

        File file = new File(filePath);
        assertThat(file.exists(), is(true));
        tempFolder.delete();
    }

    @Test
    void shouldLoadScoreGivenEmptyScore() throws IOException {
        tempFolder.create();
        File file = tempFolder.newFile("empty_score.txt");
        ScoreLoader scoreLoader = FileSystemScoreLoader.initialize(file.getAbsolutePath());

        long score = scoreLoader.loadScore();

        assertThat(score, is(0L));
        tempFolder.delete();
    }

    @Test
    void shouldLoadScore() throws IOException {
        tempFolder.create();
        File file = tempFolder.newFile("test_score.txt");
        String path = file.getAbsolutePath();
        Files.write(Paths.get(path), "153".getBytes());
        FileSystemScoreLoader scoreLoader = FileSystemScoreLoader.initialize(path);

        long score = scoreLoader.loadScore();

        assertThat(score, is(153L));
        tempFolder.delete();
    }

    @Test
    void shouldSaveScore() throws IOException {
        tempFolder.create();
        File file = tempFolder.newFile("test_score.txt");
        String path = file.getAbsolutePath();
        Files.write(Paths.get(path), "153".getBytes());
        FileSystemScoreLoader scoreLoader = FileSystemScoreLoader.initialize(path);

        scoreLoader.saveScore(86);

        String score = loadFileContent(path);
        assertThat(score, is("86"));
        tempFolder.delete();
    }

    private String loadFileContent(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}