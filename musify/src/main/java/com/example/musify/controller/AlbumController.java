package com.example.musify.controller;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/album")
@RestController
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping()
    public ResponseEntity<AlbumDTO> addAlbum(@RequestBody AlbumDTO albumDTO) {
        return new ResponseEntity<>(albumService.addAlbum(albumDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Integer id, @RequestBody AlbumDTO albumDTO) {
        return new ResponseEntity<>(albumService.updateAlbum(id, albumDTO), HttpStatus.OK);
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<List<AlbumDTO>> readAlbumsByArtistId(@PathVariable Integer id) {
        return new ResponseEntity<>(albumService.readAlbumsByArtistId(id), HttpStatus.OK);
    }

    @GetMapping("/band/{id}")
    public ResponseEntity<List<AlbumDTO>> readAlbumsByBandId(@PathVariable Integer id) {
        return new ResponseEntity<>(albumService.readAlbumsByBandId(id), HttpStatus.OK);
    }
}