package br.com.rbarbiero.feign;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.dto.album.AlbumsCollection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "searchAlbumClient", url = "${spotify.api.search.address}")
public interface SpotifyFeignClient {

    @GetMapping(value = "/albums/{id}")
    Album getAlbum(@PathVariable(value = "id") String id, @RequestHeader(value = "Authorization") String authorization);

    @GetMapping(value = "/search?q={genre}&type=album&limit={limit}")
    AlbumsCollection getAlbuns(@PathVariable(value = "genre") String genre, @PathVariable(value = "limit") int limit,
                               @RequestHeader(value = "Authorization") String authorization);
}
