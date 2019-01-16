package br.com.rbarbiero.controller;

import br.com.rbarbiero.AlbumSalesApplication;
import br.com.rbarbiero.dto.sale.Sale;
import br.com.rbarbiero.service.spotify.search.SaleSearchService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = AlbumSalesApplication.class)
@RunWith(MockitoJUnitRunner.class)
public class SaleControllerTest {

    SaleController saleController;
    Sale sale;

    @Mock
    SaleSearchService saleSearchService;

    @Before
    public void setUp() throws Exception {
        sale = new Sale();
        Mockito.when(saleSearchService.findById("id")).thenReturn(sale);
        Mockito.when(saleSearchService.findByDateRange(LocalDate.now(), LocalDate.now(), 1, 1))
                .thenReturn(Collections.singletonList(sale));
        Mockito.when(saleSearchService.createSale(sale)).thenReturn(sale);
        saleController = new SaleController(saleSearchService);
    }

    @Test
    public void getSaleById() {
        Sale sale = saleController.getSaleById("id");
        Assertions.assertThat(sale).isNotNull();
    }

    @Test
    public void getSalesByDate() {
        List<Sale> sales = saleController.getSalesByDate(LocalDate.now(), LocalDate.now(), 1, 1);
        Assertions.assertThat(sales).isNotEmpty();
    }

    @Test
    public void createSale() {
        Sale sale = saleController.createSale(this.sale);
        Assertions.assertThat(sale).isNotNull();
    }
}