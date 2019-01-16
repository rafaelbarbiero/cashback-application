package br.com.rbarbiero.service.repository;

import br.com.rbarbiero.AlbumSalesApplication;
import br.com.rbarbiero.builder.AlbumBuider;
import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import br.com.rbarbiero.service.repository.impl.AlbumRepositoryServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = AlbumSalesApplication.class)
@RunWith(SpringRunner.class)
public class AlbumRepositoryServiceTest {

    @Autowired
    AlbumRepositoryServiceImpl albumRepositoryService;

    Album album;
    Album other;

    @Before
    public void setUp() throws Exception {
        album = AlbumBuider.of(new Album("idTestAlbum", Genre.ROCK, "name", BigDecimal.ZERO, BigDecimal.ZERO))
                .generateRandomValue()
                .build();
        other = AlbumBuider.of(new Album("idTestOther", Genre.ROCK, "name2", BigDecimal.ZERO, BigDecimal.ZERO))
                .generateRandomValue()
                .build();
        albumRepositoryService.save(other);
        albumRepositoryService.save(album);
    }

    @After
    public void tearDown(){
        albumRepositoryService.delete(other);
        albumRepositoryService.delete(album);
    }

    @Test
    public void save() {
        Album album = albumRepositoryService.findById("idTestOther");
        Assertions.assertThat(album.getId()).isEqualTo(other.getId());
    }

    @Test
    public void saveAll() {
        albumRepositoryService.delete(other);
        albumRepositoryService.delete(album);
        albumRepositoryService.saveAll(Arrays.asList(this.album, this.other));

        Album album = albumRepositoryService.findById(this.album.getId());
        Assertions.assertThat(album).isNotNull();
        Assertions.assertThat(album.getName()).isEqualTo("name");
        Assertions.assertThat(album.getCashBack()).isGreaterThanOrEqualTo(BigDecimal.ZERO);

        Album other = albumRepositoryService.findById(this.other.getId());
        Assertions.assertThat(other).isNotNull();
        Assertions.assertThat(other.getName()).isEqualTo("name2");
        Assertions.assertThat(other.getCashBack()).isGreaterThanOrEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void findById() {
        Album album = albumRepositoryService.findById(this.album.getId());
        Assertions.assertThat(album).isNotNull();
        Assertions.assertThat(album.getId()).isEqualTo("idTestAlbum");
    }

}