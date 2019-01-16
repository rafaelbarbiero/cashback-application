package br.com.rbarbiero.dto.album;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class Album implements Serializable {

    @Id @NotNull @NotBlank (message = "Id must be a Spotify-ID") private String id;
    @NotNull(message = "Genre must not be null") private Genre genre;
    @NotNull(message = "Name must not be null") private String name;
    @NotNull (message = "Price must not be null")
    @Min(value = 1, message = "Price must be greater than zero ")
    private BigDecimal price;

    private BigDecimal cashBack;

    public Album() {
    }

    public Album(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Album(@NotNull @NotBlank(message = "Id must be a Spotify-ID") String id, @NotNull(message = "Genre must not be null") Genre genre, @NotNull(message = "Name must not be null") String name, @NotNull(message = "Price must not be null") @Min(value = 1, message = "Price must be greater than zero ") BigDecimal price, BigDecimal cashBack) {
        this.id = id;
        this.genre = genre;
        this.name = name;
        this.price = price;
        this.cashBack = cashBack;
    }

    public BigDecimal getCashBack() {
        return cashBack;
    }

    public void setCashBack(BigDecimal cashBack) {
        this.cashBack = cashBack;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override public String toString() {
        return super.toString();
    }
}
