package br.com.rbarbiero.service.cashback.calculator.impl;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import br.com.rbarbiero.dto.sale.Sale;
import br.com.rbarbiero.service.cashback.calculator.SaleCashbackCalculator;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleCashbackCalculatorImplTest {



    SaleCashbackCalculator saleCashbackCalculator;
    Album album;
    Sale sale;

    @Before
    public void setUp() throws Exception {
        Map<Genre, Map<DayOfWeek, Integer>> cashbackConfiguration = new HashMap<>();
        Map<DayOfWeek, Integer> genreMap = new HashMap<>();
        genreMap.put(LocalDate.now().getDayOfWeek(), 10);
        cashbackConfiguration.put(Genre.ROCK, genreMap);
        album = new Album("idTeste", Genre.ROCK, "nomeTeste", BigDecimal.valueOf(100), BigDecimal.TEN);
        List<Album> albums = Arrays.asList(album, album, album, album);
        sale = new Sale("saleId", albums, BigDecimal.ZERO, LocalDateTime.now());
        saleCashbackCalculator = new SaleCashbackCalculatorImpl();
    }

    @Test
    public void calculate() {
        BigDecimal cashbackSale = saleCashbackCalculator.calculate(sale);
        Assertions.assertThat(cashbackSale).isEqualTo(BigDecimal.valueOf(40));
    }
}