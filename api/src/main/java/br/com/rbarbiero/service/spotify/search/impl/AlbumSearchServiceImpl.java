package br.com.rbarbiero.service.spotify.search.impl;

import br.com.rbarbiero.builder.AlbumBuider;
import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.AlbumsCollection;
import br.com.rbarbiero.service.repository.AlbumRepositoryService;
import br.com.rbarbiero.service.spotify.integration.SpotifyIntegration;
import br.com.rbarbiero.service.spotify.search.AlbumSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumSearchServiceImpl implements AlbumSearchService {

    private final SpotifyIntegration spotifyIntegration;
    private final AlbumRepositoryService albumRepositoryService;

    @Autowired
    public AlbumSearchServiceImpl(SpotifyIntegration spotifyIntegration, AlbumRepositoryService albumRepositoryService) {
        this.spotifyIntegration = spotifyIntegration;
        this.albumRepositoryService = albumRepositoryService;
    }

    @Override
    @Cacheable(value = "album", key = "#p0")
    public Album getAlbum(String id) {
        final Album album = spotifyIntegration.getAlbumById(id);
        return AlbumBuider.of(album).generateRandomValue().build();
    }

    @Override
    public List<Album> getAlbumsByGenre(final String genre, int page, int size) {
        final AlbumsCollection albumsCollection = spotifyIntegration.getAlbunByGenre(genre);
        final List<Album> albumsCreated = albumsCollection
                .getAlbums()
                .getAlbumList()
                .stream()
                .map(AlbumBuider::of)
                .map(albumBuider -> albumBuider.setGenre(genre))
                .map(AlbumBuider::generateRandomValue)
                .map(AlbumBuider::build)
                .collect(Collectors.toList());
        albumRepositoryService.saveAll(albumsCreated);
        return albumRepositoryService.findAllByGenreOrderByNameAsc(genre, page, size);
    }
}
