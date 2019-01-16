package br.com.rbarbiero.service.repository.impl;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import br.com.rbarbiero.repository.AlbumRepository;
import br.com.rbarbiero.service.repository.AlbumRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumRepositoryServiceImpl implements AlbumRepositoryService {

    AlbumRepository albumRepository;

    @Autowired
    public AlbumRepositoryServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    public List<Album> saveAll(List<Album> albums) {
        return albumRepository.saveAll(albums);
    }

    @Override
    public List<Album> findAllByGenreOrderByNameAsc(String genre, int page, int size) {
        final Genre genreVal = Genre.of(genre);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("name")));
        return albumRepository.findAllByGenre(genreVal, pageRequest);
    }

    @Override
    public Album findById(final String id) {
        return albumRepository.findById(id).orElseGet(Album::new);
    }

    @Override
    public void delete(Album album){
        albumRepository.delete(album);
    }
}
