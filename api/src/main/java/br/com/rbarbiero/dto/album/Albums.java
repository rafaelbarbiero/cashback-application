package br.com.rbarbiero.dto.album;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Albums {

    private List<Album> albumList;

    public Albums() {
    }

    public Albums(List<Album> albumList) {
        this.albumList = albumList;
    }

    @JsonProperty("items")
    public List<Album> getAlbumList() {
        return albumList;
    }

    @JsonProperty("items")
    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
}
