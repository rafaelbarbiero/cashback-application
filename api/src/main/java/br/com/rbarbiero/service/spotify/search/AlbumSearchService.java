package br.com.rbarbiero.service.spotify.search;

import br.com.rbarbiero.dto.album.Album;

import java.util.List;

public interface AlbumSearchService {
    Album getAlbum(String id);
    List<Album> getAlbumsByGenre(final String genre, int page, int size);
}
