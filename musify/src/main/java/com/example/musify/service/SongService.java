package com.example.musify.service;

import com.example.musify.UserUtils;
import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongViewDTO;
import com.example.musify.exception.ResourceNotFoundException;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.SongMapper;
import com.example.musify.model.*;
import com.example.musify.repo.*;
import com.example.musify.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final PlaylistRepository playlistRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final AlternativeSongTitleRepository alternativeSongTitleRepository;
    private final SongMapper songMapper;

    @Autowired
    public SongService(SongRepository songRepository, ArtistRepository artistRepository, PlaylistRepository playlistRepository, AlbumRepository albumRepository, UserRepository userRepository, AlternativeSongTitleRepository alternativeSongTitleRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.playlistRepository = playlistRepository;
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.alternativeSongTitleRepository = alternativeSongTitleRepository;
        this.songMapper = songMapper;
    }

    @Transactional
    public List<SongViewDTO> readSongsByPlaylistId(Integer id) {
        Optional<Playlist> optional = playlistRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + id);
        }

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optional.get();

        if (playlist.getType().equals("private") && playlist.getOwnerUser().getId().intValue() != user.getId().intValue()) {
            throw new UnauthorizedException("You cannot view this private playlist");
        }

        Set<Song> songs = optional.get().getSongsInPlaylist();

        return songs
                .stream()
                .map(songMapper::toViewDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SongViewDTO> readSongsByAlbumId(Integer id) {
        Optional<Album> optional = albumRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no album with id = " + id);
        }

        Set<Song> songs = optional.get().getSongs();

        return songs
                .stream()
                .map(songMapper::toViewDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SongDTO addSong(SongDTO songDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can create new songs");
        }

        Song song = songMapper.toEntity(songDTO);
        if (!songDTO.getAlternativeTitles().isEmpty()) {
            addAlternativeTitles(song, songDTO);
        }

        if (!songDTO.getComposersIds().isEmpty()) {
            addComposersById(song, songDTO);
        }

        song = songRepository.save(song);
        return songMapper.toDto(song);
    }

    @Transactional
    public SongDTO updateSong(Integer id, SongDTO songDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can update songs");
        }

        Optional<Song> optional = songRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no song with id = " + id);
        }

        Song song = optional.get();
        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setCreatedDate(songDTO.getCreatedDate());

        if (!songDTO.getAlternativeTitles().isEmpty()) {
            clearAlternativeTitles(song);
            addAlternativeTitles(song, songDTO);
        }

        if (!songDTO.getComposersIds().isEmpty()) {
            clearComposers(song);
            addComposersById(song, songDTO);
        }

        songRepository.save(song);
        return songMapper.toDto(song);
    }

    private void clearComposers(Song song) {
        for (Artist composer : song.getComposers()) {
            composer.getComposedSongs().remove(song);
        }

        song.getComposers().clear();
    }

    private void addComposersById(Song song, SongDTO songDTO) {
        List<Artist> artists = (List<Artist>) artistRepository.findAllById(songDTO.getComposersIds());
        for (Artist artist : artists) {
            song.addComposer(artist);
        }
    }

    private void addAlternativeTitles(Song song, SongDTO songDTO) {
        for (String title : songDTO.getAlternativeTitles()) {
            AlternativeSongTitle newTitle = new AlternativeSongTitle();
            newTitle.setId(0);
            newTitle.setAlternativeTitle(title);
            newTitle.addSong(song);
            alternativeSongTitleRepository.save(newTitle);
        }
    }

    private void clearAlternativeTitles(Song song) {
        for (AlternativeSongTitle title : song.getAlternativeSongTitles()) {
            title.setSong(null);
            alternativeSongTitleRepository.delete(title);
        }

        song.getAlternativeSongTitles().clear();
    }


    public List<Song> getSongs() {
        return songRepository.findAll();
    }
}
