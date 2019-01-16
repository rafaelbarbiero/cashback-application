package br.com.rbarbiero.service.spotify.integration.impl;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.AlbumsCollection;
import br.com.rbarbiero.dto.authentication.AccessToken;
import br.com.rbarbiero.feign.SpotifyAuthenticationFeignClient;
import br.com.rbarbiero.feign.SpotifyFeignClient;
import br.com.rbarbiero.service.spotify.integration.SpotifyIntegration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class SpotifyIntegrationImpl implements SpotifyIntegration {

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final Logger LOG = LogManager.getLogger(SpotifyIntegration.class);

    final SpotifyAuthenticationFeignClient authenticationClient;
    final SpotifyFeignClient searchSpotifyClient;

    @Value("${spotify.api.authorization.address}")
    private String apiAuthenticationHost;

    @Value("Basic ${spotify.api.authorization.token.basic}")
    private String authorization;

    @Value("${spotify.api.authorization.grant-type}")
    private String grantType;

    @Autowired
    SpotifyIntegrationImpl(SpotifyAuthenticationFeignClient authenticationClient, SpotifyFeignClient searchSpotifyClient) {
        this.authenticationClient = authenticationClient;
        this.searchSpotifyClient = searchSpotifyClient;
    }

    @Override
    public Album getAlbumById(final String id) {
        LOG.info("Looking at spotify");
        final AccessToken accessToken = authenticationClient.getBearerToken(grantType, CONTENT_TYPE, authorization);
        final String authorizationBearer = String.format("%s %s", accessToken.getTokenType(), accessToken.getToken());
        return searchSpotifyClient.getAlbum(id, authorizationBearer);
    }

    @Override
    public AlbumsCollection getAlbunByGenre(final String genre) {
        LOG.info("Looking at spotify");
        final AccessToken accessToken = authenticationClient.getBearerToken(grantType, CONTENT_TYPE, authorization);
        final String authorizationBearer = String.format("%s %s", accessToken.getTokenType(), accessToken.getToken());
        return searchSpotifyClient.getAlbuns(genre, 50, authorizationBearer);
    }
}
