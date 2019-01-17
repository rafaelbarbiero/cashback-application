package br.com.rbarbiero.service.repository.impl;

import br.com.rbarbiero.dto.sale.Sale;
import br.com.rbarbiero.repository.SaleRepository;
import br.com.rbarbiero.service.repository.SaleRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleRepositoryServiceImpl implements SaleRepositoryService {

    SaleRepository saleRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    public SaleRepositoryServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale save(Sale sale){
        return saleRepository.save(sale);
    }

    @Override
    public Optional<Sale> findById(final String id){
        return saleRepository.findById(id);
    }

    @Override
    public List<Sale> findByDateRange(LocalDate start, LocalDate end, Integer page, Integer size){
        final Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("saleDate")));
        Query query = new Query();
        query.addCriteria(Criteria.where("saleDate").gte(start).lte(end));
        query.with(pageable);
        return mongoTemplate.find(query, Sale.class);
    }

    @Override
    public void delete(Sale sale){
        saleRepository.delete(sale);
    }

}
