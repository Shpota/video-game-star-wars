package com.shpota.starwars.io;

import com.shpota.starwars.exception.GameIOException;
import com.shpota.starwars.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemScoreLoader implements ScoreLoader {
    private final String filePath;

    private FileSystemScoreLoader(String filePath) {
        this.filePath = filePath;
    }

    public static FileSystemScoreLoader initialize(String filePath) {
        Assert.notNull(filePath, "filePath must not be null");
        try {
            new File(filePath).createNewFile();
        } catch (IOException e) {
            throw new GameIOException(e);
        }
        return new FileSystemScoreLoader(filePath);
    }

    @Override
    public long loadScore() {
        Path path = Paths.get(filePath);
        try {
            return Files.lines(path)
                    .findFirst()
                    .map(Long::parseLong)
                    .orElse(0L);
        } catch (IOException e) {
            throw new GameIOException(e);
        }
    }

    @Override
    public void saveScore(long score) {
        Path path = Paths.get(filePath);
        byte[] bytes = String.valueOf(score).getBytes();
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new GameIOException(e);
        }
    }
}
