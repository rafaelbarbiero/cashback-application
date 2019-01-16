package br.com.rbarbiero.service.spotify.search;

import br.com.rbarbiero.dto.sale.Sale;

import java.time.LocalDate;
import java.util.List;

public interface SaleSearchService {
    Sale createSale(Sale sale);

    List<Sale> findByDateRange(LocalDate start, LocalDate end, Integer page, Integer size);

    Sale findById(String id);
}
