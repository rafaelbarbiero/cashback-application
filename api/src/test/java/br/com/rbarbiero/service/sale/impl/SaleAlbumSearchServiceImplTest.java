package br.com.rbarbiero.service.sale.impl;

import br.com.rbarbiero.builder.AlbumBuider;
import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import br.com.rbarbiero.dto.sale.Sale;
import br.com.rbarbiero.service.cashback.calculator.impl.AlbumCashbackCalculatorImpl;
import br.com.rbarbiero.service.cashback.calculator.impl.SaleCashbackCalculatorImpl;
import br.com.rbarbiero.service.repository.SaleRepositoryService;
import br.com.rbarbiero.service.spotify.search.SaleSearchService;
import br.com.rbarbiero.service.spotify.search.impl.SaleSearchServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SaleAlbumSearchServiceImplTest {


    @Mock
    SaleRepositoryService saleRepositoryService;
    SaleSearchService saleSearchService;
    Sale sale;
    Album album;
    Album other;
    LocalDateTime saleDate;
    LocalDate start;
    LocalDate end;

    @Before
    public void setUp() throws Exception {

        Map<Genre, Map<DayOfWeek, Integer>> cashbackConfiguration = new HashMap<>();
        Map<DayOfWeek, Integer> genreMap = new HashMap<>();
        genreMap.put(LocalDateTime.now().getDayOfWeek(), 5);
        cashbackConfiguration.put(Genre.ROCK, genreMap);

        saleDate = LocalDateTime.now();
        album = AlbumBuider.of(new Album("idTestAlbum", Genre.ROCK, "name", BigDecimal.ZERO, BigDecimal.ZERO))
                .build();
        other = AlbumBuider.of(new Album("idTestOther", Genre.ROCK, "name2", BigDecimal.ZERO, BigDecimal.ZERO))
                .build();
        sale = new Sale("idTest", Arrays.asList(album, other), BigDecimal.ZERO, saleDate);

        start = LocalDate.now().minusDays(1);
        end = LocalDate.now().plusDays(1);

        Mockito.when(saleRepositoryService.save(sale)).thenReturn(sale);
        Mockito.when(saleRepositoryService.findById("idTest")).thenReturn(Optional.of(sale));
        Mockito.when(saleRepositoryService.findByDateRange(start, end, 1, 10)).thenReturn(Arrays.asList(sale));
        saleSearchService = new SaleSearchServiceImpl(saleRepositoryService, new SaleCashbackCalculatorImpl(), new AlbumCashbackCalculatorImpl(cashbackConfiguration));

    }

    @Test
    public void createSale() {
        Sale sale = saleSearchService.createSale(this.sale);
        Assertions.assertThat(sale.getCashBack()).isGreaterThanOrEqualTo(BigDecimal.ZERO);
        Assertions.assertThat(sale.getAlbums()).isNotEmpty();
        Assertions.assertThat(sale.getSaleDate()).isAfterOrEqualTo(saleDate);
        Assertions.assertThat(sale.getId()).isEqualTo("idTest");
    }

    @Test
    public void findByDateRange() {
        List<Sale> sales = saleSearchService.findByDateRange(start, end, 1, 10);
        Assertions.assertThat(sales).isNotEmpty();
    }

    @Test
    public void findById() {
        Sale sale = saleSearchService.findById("idTest");
        Assertions.assertThat(sale).isNotNull();
        Assertions.assertThat(sale.getId()).isEqualTo("idTest");
        Assertions.assertThat(sale.getAlbums()).isNotEmpty();
    }
}