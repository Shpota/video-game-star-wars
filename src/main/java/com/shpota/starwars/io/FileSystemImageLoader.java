package com.shpota.starwars.io;

import com.shpota.starwars.exception.GameIOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemImageLoader implements ImageLoader {
    @Override
    public BufferedImage loadImage(String path) {
        try {
            InputStream stream = FileSystemImageLoader.class.getResourceAsStream(path);
            return ImageIO.read(stream);
        } catch (IOException e) {
            throw new GameIOException(e);
        }
    }
}
