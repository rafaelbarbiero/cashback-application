package br.com.rbarbiero.service.repository;

import br.com.rbarbiero.builder.AlbumBuider;
import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import br.com.rbarbiero.dto.sale.Sale;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SaleRepositoryServiceTest {

    Sale sale;
    Album album;
    Album other;

    @Autowired
    SaleRepositoryService saleRepositoryService;

    @Before
    public void setUp(){
        album = AlbumBuider.of(new Album("idTest", Genre.ROCK, "name", BigDecimal.ZERO, BigDecimal.ZERO))
                .build();
        other = AlbumBuider.of(new Album("idTest", Genre.ROCK, "name2", BigDecimal.ZERO, BigDecimal.ZERO))
                .build();
        List<Album> albums = Arrays.asList(album, other);
        sale = new Sale("idSaleTeste", albums, BigDecimal.ZERO, LocalDateTime.now());
        sale = saleRepositoryService.save(sale);
    }

    @After
    public void tearDown() throws Exception {
        saleRepositoryService.delete(sale);
    }

    @Test
    public void save() {
        saleRepositoryService.delete(sale);
        Sale sale = saleRepositoryService.save(this.sale);
        Assertions.assertThat(sale).isNotNull();
        Assertions.assertThat(sale.getId()).isEqualTo("idSaleTeste");
    }

    @Test
    public void findById() {
        Sale sale = saleRepositoryService.findById("idSaleTeste").get();
        Assertions.assertThat(sale.getId()).isEqualTo("idSaleTeste");
    }

}