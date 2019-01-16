package br.com.rbarbiero.dto.album;

import br.com.rbarbiero.exception.GenreNotFoundException;

import java.util.Arrays;

public enum Genre {

    POP, MPB, CLASSIC, ROCK;

    public static Genre of(final String genre) {
        return Arrays.stream(Genre.values())
                .filter(genreVal -> genreVal.name().equalsIgnoreCase(genre))
                .findFirst()
                .orElseThrow(() -> new GenreNotFoundException(String.format("Genre %s not found", genre)));
    }

}
