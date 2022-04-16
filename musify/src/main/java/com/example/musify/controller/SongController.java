package com.example.musify.controller;

import com.example.musify.dto.SongDTO;
import com.example.musify.model.Song;
import com.example.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/song")
@RestController
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping()
    public ResponseEntity<List<Song>> getSongs() {
        return new ResponseEntity<>(songService.getSongs(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SongDTO> addSong(@RequestBody SongDTO songDTO) {
        return new ResponseEntity<>(songService.addSong(songDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Integer id, @RequestBody SongDTO songDTO) {
        return new ResponseEntity<>(songService.updateSong(id, songDTO), HttpStatus.OK);
    }
}
