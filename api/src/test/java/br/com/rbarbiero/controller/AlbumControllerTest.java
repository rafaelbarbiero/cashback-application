package br.com.rbarbiero.controller;

import br.com.rbarbiero.AlbumSalesApplication;
import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
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
public class AlbumControllerTest {

    AlbumController albumController;
    @Mock
    AlbumSearchService service;

    @Before
    public void setUp() throws Exception {
        Album album = new Album("1v1i6nqVmLWXKv2a7ByqxJ", Genre.ROCK, "name", BigDecimal.TEN, BigDecimal.ZERO);
        Mockito.when(service.getAlbum("1v1i6nqVmLWXKv2a7ByqxJ")).thenReturn(album);
        Mockito.when(service.getAlbumsByGenre("rock", 1, 1)).thenReturn(Collections.singletonList(album));
        albumController = new AlbumController(service);
    }

    @Test
    public void findAllByGenre() {
        List<Album> albums = albumController.findAllByGenre("rock", 1, 1);
        Assertions.assertThat(albums)
                .isNotNull()
                .isNotEmpty();
        Assertions.assertThat(albums.size()).isEqualTo(1);
    }

    @Test
    public void findbyId() {
        Album album = albumController.findbyId("1v1i6nqVmLWXKv2a7ByqxJ");
        Assertions.assertThat(album).isNotNull();
    }
}