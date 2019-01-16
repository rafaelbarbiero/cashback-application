package br.com.rbarbiero.repository;

import br.com.rbarbiero.dto.sale.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends MongoRepository<Sale, String> {
    List<Sale> findBySaleDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
