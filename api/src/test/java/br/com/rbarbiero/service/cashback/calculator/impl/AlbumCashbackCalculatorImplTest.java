package br.com.rbarbiero.service.cashback.calculator.impl;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import br.com.rbarbiero.service.cashback.calculator.AlbumCashbackCalculator;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AlbumCashbackCalculatorImplTest {


    AlbumCashbackCalculator albumCashbackCalculator;
    Album album;

    @Before
    public void setUp() throws Exception {
        Map<Genre, Map<DayOfWeek, Integer>> cashbackConfiguration = new HashMap<>();
        Map<DayOfWeek, Integer> genreMap = new HashMap<>();
        genreMap.put(LocalDate.now().getDayOfWeek(), 18);
        cashbackConfiguration.put(Genre.ROCK, genreMap);
        albumCashbackCalculator = new AlbumCashbackCalculatorImpl(cashbackConfiguration);
        album = new Album("idTeste", Genre.ROCK, "nomeTeste", BigDecimal.valueOf(100), BigDecimal.ZERO);
    }

    @Test
    public void calculate() {
        BigDecimal cashback = albumCashbackCalculator.calculate(album);
        Assertions.assertThat(cashback)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(BigDecimal.valueOf(18.0));
    }
}