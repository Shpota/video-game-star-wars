package com.shpota.starwars.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertTest {
    @Test
    void shouldCheckNotNull() {
        Object object = null;
        String message = "test";

        Executable test = () -> Assert.notNull(object, message);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, test);
        assertThat(exception.getMessage(), is(message));
    }

    @Test
    void shouldCheckTrue() {
        boolean condition = false;
        String message = "test";

        Executable test = () -> Assert.isTrue(condition, message);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, test);
        assertThat(exception.getMessage(), is(message));
    }
}