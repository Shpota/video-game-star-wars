package com.shpota.starwars.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.ObjectArrayArguments.create;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MortalTest {

    @ParameterizedTest
    @MethodSource(names = "testProvider")
    void shouldBeKilled(int value, boolean result) {
        Mortal mortalMock = mock(Mortal.class);
        given(mortalMock.getHealth()).willReturn(value);
        Mortal mortal = new Mortal() {
            @Override
            public int getHealth() {
                return mortalMock.getHealth();
            }

            @Override
            public void addHealth(int value) {
            }
        };

        boolean killed = mortal.isKilled();

        assertThat(killed, is(result));
    }

    static Stream<Arguments> testProvider() {
        return Stream.of(
                create(1, false),
                create(-1, true),
                create(0, true)
        );
    }

    @Test
    void shouldKill() {
        Mortal mortalMock = mock(Mortal.class);
        given(mortalMock.getHealth()).willReturn(100);
        Mortal mortal = new Mortal() {
            @Override
            public int getHealth() {
                return mortalMock.getHealth();
            }

            @Override
            public void addHealth(int value) {
                mortalMock.addHealth(value);
            }
        };

        mortal.kill();

        verify(mortalMock).addHealth(-100);
    }
}