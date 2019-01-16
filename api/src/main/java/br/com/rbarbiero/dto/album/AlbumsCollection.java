package br.com.rbarbiero.dto.album;

public class AlbumsCollection {

    Albums albums;

    public AlbumsCollection() {
    }

    public AlbumsCollection(Albums albums) {
        this.albums = albums;
    }

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }
}
