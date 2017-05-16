package com.shpota.starwars.io;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class FileSystemImageLoaderTest {
    @Test
    void shouldLoadImage() {
        ImageLoader imageLoader = new FileSystemImageLoader();
        String path = "/test_image.png";

        BufferedImage image = imageLoader.loadImage(path);

        assertThat(image, is(notNullValue()));
    }
}