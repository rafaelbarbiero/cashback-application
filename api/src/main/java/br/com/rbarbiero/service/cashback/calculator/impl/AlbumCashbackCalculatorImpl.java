package br.com.rbarbiero.service.cashback.calculator.impl;


import br.com.rbarbiero.service.cashback.calculator.AlbumCashbackCalculator;
import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

@Service
public class AlbumCashbackCalculatorImpl implements AlbumCashbackCalculator {

    private final Map<Genre, Map<DayOfWeek, Integer>> cashbackConfiguration;

    @Autowired
    public AlbumCashbackCalculatorImpl(Map<Genre, Map<DayOfWeek, Integer>> cashbackConfiguration) {
        this.cashbackConfiguration = cashbackConfiguration;
    }

    @Override
    public BigDecimal calculate(Album album) {
        int percents = cashbackConfiguration.get(album.getGenre()).get(LocalDate.now().getDayOfWeek());
        return BigDecimal.valueOf((album.getPrice().doubleValue() * percents) / 100);
    }
}
