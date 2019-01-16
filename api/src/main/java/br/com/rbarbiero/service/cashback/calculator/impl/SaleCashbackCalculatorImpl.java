package br.com.rbarbiero.service.cashback.calculator.impl;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.sale.Sale;
import br.com.rbarbiero.service.cashback.calculator.SaleCashbackCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SaleCashbackCalculatorImpl implements SaleCashbackCalculator {

    @Override
    public BigDecimal calculate(Sale sale) {
        return sale.getAlbums().stream()
                .map(Album::getCashBack)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
