package br.com.rbarbiero.repository;

import br.com.rbarbiero.dto.sale.Sale;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SaleRepository extends MongoRepository<Sale, String> {
}
