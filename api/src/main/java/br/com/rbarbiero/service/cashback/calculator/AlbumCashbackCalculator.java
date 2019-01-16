package br.com.rbarbiero.service.cashback.calculator;

import br.com.rbarbiero.dto.album.Album;

import java.math.BigDecimal;

public interface AlbumCashbackCalculator {
    BigDecimal calculate(Album album);
}
