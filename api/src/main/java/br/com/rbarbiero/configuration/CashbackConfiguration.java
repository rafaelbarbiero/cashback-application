package br.com.rbarbiero.configuration;

import br.com.rbarbiero.dto.album.Genre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CashbackConfiguration {

    @Value("#{${cashback.genre.pop}}")
    Map<DayOfWeek, Integer> pop;

    @Value("#{${cashback.genre.rock}}")
    Map<DayOfWeek, Integer> rock;

    @Value("#{${cashback.genre.classic}}")
    Map<DayOfWeek, Integer> classic;

    @Value("#{${cashback.genre.mpb}}")
    Map<DayOfWeek, Integer> mpb;

    @Bean
    public Map<Genre, Map<DayOfWeek, Integer>> createCashbackConfig(){
        final Map<Genre, Map<DayOfWeek, Integer>> mapConfig = new EnumMap<>(Genre.class);
        mapConfig.put(Genre.POP, pop);
        mapConfig.put(Genre.ROCK, rock);
        mapConfig.put(Genre.MPB, mpb);
        mapConfig.put(Genre.CLASSIC, classic);
        return mapConfig;
    }
}
