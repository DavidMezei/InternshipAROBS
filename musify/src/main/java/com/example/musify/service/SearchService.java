package com.example.musify.service;

import com.example.musify.dto.SearchViewDTO;
import com.example.musify.mapper.AlbumMapper;
import com.example.musify.mapper.ArtistMapper;
import com.example.musify.mapper.BandMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.AlbumRepository;
import com.example.musify.repo.ArtistRepository;
import com.example.musify.repo.BandRepository;
import com.example.musify.repo.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final BandRepository bandRepository;
    private final ArtistMapper artistMapper;
    private final BandMapper bandMapper;
    private final SongMapper songMapper;
    private final AlbumMapper albumMapper;

    @Autowired
    public SearchService(AlbumRepository albumRepository, SongRepository songRepository, ArtistRepository artistRepository, BandRepository bandRepository, ArtistMapper artistMapper, BandMapper bandMapper, SongMapper songMapper, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.bandRepository = bandRepository;
        this.artistMapper = artistMapper;
        this.bandMapper = bandMapper;
        this.songMapper = songMapper;
        this.albumMapper = albumMapper;
    }

    @Transactional
    public SearchViewDTO search(String searchTerm) {
        SearchViewDTO searchViewDTO = new SearchViewDTO();
        searchViewDTO.setArtists(artistMapper.toDTOList(artistRepository.findAllByStageNameContainingIgnoreCase(searchTerm)));
        searchViewDTO.setBands(bandMapper.toDTOList(bandRepository.findAllByBandNameContainingIgnoreCase(searchTerm)));
        searchViewDTO.setAlbums(albumMapper.toDTOList(albumRepository.findAllByTitleContainingIgnoreCase(searchTerm)));
        searchViewDTO.setSongs(songMapper.toViewDTOList(songRepository.findAllByTitleContainingIgnoreCase(searchTerm)));

        return searchViewDTO;
    }
}
