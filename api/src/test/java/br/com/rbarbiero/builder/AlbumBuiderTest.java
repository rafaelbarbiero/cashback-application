package br.com.rbarbiero.builder;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AlbumBuiderTest {

    Album album;

    @Before
    public void setUp() throws Exception {
        album = new Album("NomeAlbum", "IdAlbum");
    }

    @Test
    public void of() {
        AlbumBuider albumBuider = new AlbumBuider(album);
        AlbumBuider other = AlbumBuider.of(album);
        Assertions.assertThat(other).isNotEqualTo(albumBuider);
    }

    @Test
    public void setGenre() {
        Genre rock = Genre.ROCK;
        Genre genre = AlbumBuider.of(album).setGenre("rock").build().getGenre();
        Assertions.assertThat(genre).isEqualTo(rock);
    }

    @Test
    public void generateRandomValue() {
        BigDecimal price = AlbumBuider.of(album).generateRandomValue().build().getPrice();
        Assertions.assertThat(price)
                .isNotNull()
                .isNotNegative()
                .isGreaterThanOrEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void build() {
        Album other = AlbumBuider.of(album).generateRandomValue().setGenre("rock").build();
        Assertions.assertThat(other)
                .isNotNull();
    }
}