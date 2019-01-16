package br.com.rbarbiero.controller;

import br.com.rbarbiero.dto.album.Album;
import br.com.rbarbiero.service.spotify.search.AlbumSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlbumController {

    AlbumSearchService albumSearchService;

    @Autowired
    public AlbumController(AlbumSearchService albumSearchService) {
        this.albumSearchService = albumSearchService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/albums", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Album> findAllByGenre(
            @RequestParam("genre") String genre,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size) {
        return albumSearchService.getAlbumsByGenre(genre, page, size);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/albums/id/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Album findbyId(@PathVariable("id") String id) {
        return albumSearchService.getAlbum(id);
    }

}
