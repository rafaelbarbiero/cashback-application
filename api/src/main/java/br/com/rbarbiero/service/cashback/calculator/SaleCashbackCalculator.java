package br.com.rbarbiero.service.cashback.calculator;

import br.com.rbarbiero.dto.sale.Sale;

import java.math.BigDecimal;

public interface SaleCashbackCalculator {
    BigDecimal calculate(Sale sale);
}
