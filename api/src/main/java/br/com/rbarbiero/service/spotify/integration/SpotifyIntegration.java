package br.com.rbarbiero.service.spotify.integration;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.AlbumsCollection;

public interface SpotifyIntegration {
    Album getAlbumById(String id);
    AlbumsCollection getAlbunByGenre(final String genre);
}
