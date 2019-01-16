package br.com.rbarbiero.service.spotify.integration.impl;

import br.com.rbarbiero.AlbumSalesApplication;
import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.AlbumsCollection;
import br.com.rbarbiero.service.spotify.integration.SpotifyIntegration;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AlbumSalesApplication.class)
@RunWith(SpringRunner.class)
public class SpotifyIntegrationImplTest {

    @Autowired
    SpotifyIntegration spotifyIntegration;

    @Test
    public void getAlbumById() {
        Album album = spotifyIntegration.getAlbumById("1DILNh7maaYyKxe15V9xLq");
        Assertions.assertThat(album).isNotNull();
    }

    @Test
    public void getAlbunByGenre() {
        AlbumsCollection albumsCollection = spotifyIntegration.getAlbunByGenre("rock");
        Assertions.assertThat(albumsCollection).isNotNull();
        Assertions.assertThat(albumsCollection.getAlbums().getAlbumList()).isNotEmpty();
    }
}