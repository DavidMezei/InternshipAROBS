package com.example.musify.controller;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/artists")
@RestController
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping()
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        return new ResponseEntity<>(artistService.getArtists(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Integer id) {
        return new ResponseEntity<>(artistService.getArtistById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ArtistDTO> addArtist(@RequestBody @Valid ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.addArtist(artistDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Integer id, @RequestBody @Valid ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.updateArtist(id, artistDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtistById(@PathVariable Integer id) {
        return new ResponseEntity<>(artistService.deleteArtistById(id), HttpStatus.OK);
    }
}
