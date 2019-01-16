package br.com.rbarbiero.dto.sale;

import br.com.rbarbiero.dto.album.Album;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Sale {

    @Id String id;
    @Valid List<Album> albums;
    private BigDecimal cashBack;
    LocalDateTime saleDate;

    public Sale() {
    }

    public Sale(String id, List<Album> albums, BigDecimal cashBack, LocalDateTime saleDate) {
        this.id = id;
        this.albums = albums;
        this.cashBack = cashBack;
        this.saleDate = saleDate;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public BigDecimal getCashBack() {
        return cashBack;
    }

    public void setCashBack(BigDecimal cashBack) {
        this.cashBack = cashBack;
    }
}
