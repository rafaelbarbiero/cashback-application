package br.com.rbarbiero.controller;

import br.com.rbarbiero.dto.sale.Sale;
import br.com.rbarbiero.service.spotify.search.SaleSearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SaleController {

    final SaleSearchService saleSearchService;

    public SaleController(SaleSearchService saleSearchService) {
        this.saleSearchService = saleSearchService;
    }

    @GetMapping(value = "/sales/{id}")
    public Sale getSaleById(final @PathVariable("id") String id) {
        return saleSearchService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sales", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Sale> getSalesByDate(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return saleSearchService.findByDateRange(start, end, page, size);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/sales", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Sale createSale(@RequestBody @Valid Sale sale) {
        return saleSearchService.createSale(sale);
    }

}
