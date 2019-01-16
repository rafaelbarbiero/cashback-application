package br.com.rbarbiero.service.repository;

import br.com.rbarbiero.dto.sale.Sale;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaleRepositoryService {
    Sale save(Sale sale);

    Optional<Sale> findById(String id);

    List<Sale> findByDateRange(LocalDate start, LocalDate end, Integer page, Integer size);

    void delete(Sale sale);
}
