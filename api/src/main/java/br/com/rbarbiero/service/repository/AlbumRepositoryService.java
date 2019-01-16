package br.com.rbarbiero.service.repository;

import br.com.rbarbiero.dto.album.Album;

import java.util.List;

public interface AlbumRepositoryService {
    void save(Album album);

    List<Album> saveAll(List<Album> albums);

    List<Album> findAllByGenreOrderByNameAsc(String genre, int page, int size);

    Album findById(String id);

    void delete(Album album);
}
