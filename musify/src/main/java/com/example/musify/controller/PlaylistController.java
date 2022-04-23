package com.example.musify.controller;

import java.util.List;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlists")
    public ResponseEntity<List<PlaylistDTO>> readUserPlaylists() {
        return new ResponseEntity<>(playlistService.readUserPlaylists(), HttpStatus.OK);
    }

    @PostMapping("/{playlistId}/remove/song/{songId}")
    public ResponseEntity<PlaylistDTO> removeSongFromPlaylist(@PathVariable Integer playlistId, @PathVariable Integer songId) {
        return new ResponseEntity<>(playlistService.removeSongFromPlaylist(playlistId, songId), HttpStatus.OK);
    }

    @PostMapping("/playlist")
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        return new ResponseEntity<>(playlistService.createPlaylist(playlistDTO), HttpStatus.OK);
    }

    @PutMapping("/playlist/{id}")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable Integer id, @RequestBody PlaylistDTO playlistDTO) {
        return new ResponseEntity<>(playlistService.updatePlaylist(id, playlistDTO), HttpStatus.OK);
    }

    @DeleteMapping("/playlist/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Integer id) {
        return new ResponseEntity<>(playlistService.deletePlaylist(id), HttpStatus.OK);
    }

    @PostMapping("/playlist/{playlistId}/add/song/{songId}")
    public ResponseEntity<PlaylistDTO> addSongToPlaylist(@PathVariable Integer playlistId, @PathVariable Integer songId) {
        return new ResponseEntity<>(playlistService.addSongToPlaylist(playlistId, songId), HttpStatus.OK);
    }

    // TODO remove songs from playlist

    @PostMapping("/playlist/{playlistId}/add/album/{albumId}")
    public ResponseEntity<PlaylistDTO> addAlbumToPlaylist(@PathVariable Integer playlistId, @PathVariable Integer albumId) {
        return new ResponseEntity<>(playlistService.addAlbumToPlaylist(playlistId, albumId), HttpStatus.OK);
    }

    @PostMapping("/playlist/{id}/follow")
    public ResponseEntity<String> followPlaylist(@PathVariable Integer id) {
        return new ResponseEntity<>(playlistService.followPlaylist(id), HttpStatus.OK);
    }

    @PostMapping("/playlist/{id}/unfollow")
    public ResponseEntity<String> unfollowPlaylist(@PathVariable Integer id) {
        return new ResponseEntity<>(playlistService.unfollowPlaylist(id), HttpStatus.OK);
    }
}