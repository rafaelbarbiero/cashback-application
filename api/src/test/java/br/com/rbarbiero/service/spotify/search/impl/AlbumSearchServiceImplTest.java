package br.com.rbarbiero.service.spotify.search.impl;

import br.com.rbarbiero.AlbumSalesApplication;
import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Albums;
import br.com.rbarbiero.dto.album.AlbumsCollection;
import br.com.rbarbiero.dto.album.Genre;
import br.com.rbarbiero.service.repository.AlbumRepositoryService;
import br.com.rbarbiero.service.spotify.integration.SpotifyIntegration;
import br.com.rbarbiero.service.spotify.search.AlbumSearchService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = AlbumSalesApplication.class)
@RunWith(MockitoJUnitRunner.class)
public class AlbumSearchServiceImplTest {

    String genre = "rock";
    AlbumSearchService albumSearchService;
    Album album;
    AlbumsCollection albumsCollection;

    @Mock
    AlbumRepositoryService albumRepositoryService;
    @Mock SpotifyIntegration spotifyIntegration;

    @Before
    public void setUp(){
        album = new Album("1v1i6nqVmLWXKv2a7ByqxJ", Genre.ROCK, "name", BigDecimal.ZERO, BigDecimal.ZERO);
        albumsCollection = new AlbumsCollection(new Albums(Collections.singletonList(album)));

        Mockito.when(spotifyIntegration.getAlbumById("1v1i6nqVmLWXKv2a7ByqxJ")).thenReturn(album);
        Mockito.when(spotifyIntegration.getAlbunByGenre(genre)).thenReturn(albumsCollection);
        Mockito.when(albumRepositoryService.saveAll(albumsCollection.getAlbums().getAlbumList())).thenReturn(Collections.singletonList(album));
        Mockito.when(albumRepositoryService.findAllByGenreOrderByNameAsc(genre, 1, 50)).thenReturn(Collections.singletonList(album));
        albumSearchService = new AlbumSearchServiceImpl(spotifyIntegration, albumRepositoryService);
    }

    @Test
    public void getAlbum() {
        Album album = albumSearchService.getAlbum("1v1i6nqVmLWXKv2a7ByqxJ");
        Assertions.assertThat(album).isNotNull();
        Assertions.assertThat(album.getPrice()).isGreaterThanOrEqualTo(BigDecimal.ONE);
        Assertions.assertThat(album.getCashBack()).isGreaterThanOrEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void getAlbumsByGenre() {
        List<Album> albums = albumSearchService.getAlbumsByGenre(genre, 1, 50);
        Assertions.assertThat(albums).isNotEmpty();
    }
}