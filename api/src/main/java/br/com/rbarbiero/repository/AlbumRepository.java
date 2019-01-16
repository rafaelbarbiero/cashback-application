package br.com.rbarbiero.repository;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlbumRepository extends MongoRepository<Album, String> {
    List<Album> findAllByGenre(Genre genre, Pageable pageable);
    List<Album> findByGenre(Genre genre);
}
