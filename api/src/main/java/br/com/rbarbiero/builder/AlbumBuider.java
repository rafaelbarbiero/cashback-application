package br.com.rbarbiero.builder;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class AlbumBuider {

    Album album;

    public AlbumBuider(Album album) {
        this.album = album;
    }

    public static AlbumBuider of(Album album) {
        return new AlbumBuider(album);
    }

    public AlbumBuider setGenre(final String genre) {
        this.album.setGenre(Genre.of(genre));
        return this;
    }

    public AlbumBuider generateRandomValue() {
        double random = ThreadLocalRandom.current().nextDouble(1, 50);
        BigDecimal randomPrice = BigDecimal.valueOf(random).setScale(2, BigDecimal.ROUND_UP);
        this.album.setPrice(randomPrice);
        return this;
    }

    public Album build() {
        return this.album;
    }
}
