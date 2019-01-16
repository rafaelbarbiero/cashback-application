package br.com.rbarbiero.service.spotify.search.impl;

import br.com.rbarbiero.service.cashback.calculator.AlbumCashbackCalculator;
import br.com.rbarbiero.dto.sale.Sale;
import br.com.rbarbiero.service.cashback.calculator.SaleCashbackCalculator;
import br.com.rbarbiero.service.repository.SaleRepositoryService;
import br.com.rbarbiero.service.spotify.search.SaleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SaleSearchServiceImpl implements SaleSearchService {

    final SaleRepositoryService saleRepositoryService;
    final SaleCashbackCalculator saleCashbackCalculator;
    final AlbumCashbackCalculator albumCashbackCalculator;

    @Autowired
    public SaleSearchServiceImpl(SaleRepositoryService saleRepositoryService, SaleCashbackCalculator saleCashbackCalculator, AlbumCashbackCalculator albumCashbackCalculator) {
        this.saleRepositoryService = saleRepositoryService;
        this.saleCashbackCalculator = saleCashbackCalculator;
        this.albumCashbackCalculator = albumCashbackCalculator;
    }

    @Override
    public Sale createSale(final Sale sale) {
        sale.getAlbums().forEach(album -> album.setCashBack(albumCashbackCalculator.calculate(album)));
        BigDecimal cashback = saleCashbackCalculator.calculate(sale);
        sale.setCashBack(cashback);
        sale.setSaleDate(LocalDateTime.now());
        saleRepositoryService.save(sale);
        return sale;
    }

    @Override
    public List<Sale> findByDateRange(LocalDate start, LocalDate end, Integer page, Integer size) {
        return saleRepositoryService.findByDateRange(start, end, page, size);
    }

    @Override
    public Sale findById(final String id) {
        return saleRepositoryService.findById(id).orElseGet(Sale::new);
    }

}
