package com.example.musify.service;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.exception.ResourceNotFoundException;
import com.example.musify.mapper.ArtistMapper;
import com.example.musify.model.Artist;
import com.example.musify.repo.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public List<ArtistDTO> getArtists() {
        return artistMapper.toDTOList(artistRepository.findAll());
    }

    public ArtistDTO getArtistById(Integer id) {
        Optional<Artist> optional = artistRepository.findById(id);
        if(optional.isPresent()) {
            return artistMapper.toDto(optional.get());
        } else {
            throw new ResourceNotFoundException("There is no artist with id = " + id);
        }
    }

    public ArtistDTO addArtist(ArtistDTO artistDTO) {
        Artist artist = artistMapper.toEntity(artistDTO);
        artist.setId(null);
        artist = artistRepository.save(artist);

        return artistMapper.toDto(artist);
    }

    @Transactional
    public ArtistDTO updateArtist(Integer id, ArtistDTO artistDTO) {
        Optional<Artist> optional = artistRepository.findById(id);
        if(optional.isPresent()) {
            Artist artist = optional.get();
            artist.setFirstName(artistDTO.getFirstName());
            artist.setLastName(artistDTO.getLastName());
            artist.setStageName(artistDTO.getStageName());
            artist.setBirthday(artistDTO.getBirthday());
            artist.setActivityStartDate(artistDTO.getActivityStartDate());
            artist.setActivityEndDate(artistDTO.getActivityEndDate());
            artistRepository.save(artist);

            return artistMapper.toDto(artist);
        } else {
            throw new ResourceNotFoundException("There is no artist with id = " + id);
        }
    }

    @Transactional
    public String deleteArtistById(Integer id) {
        Optional<Artist> optional = artistRepository.findById(id);
        if(optional.isPresent()) {
            artistRepository.delete(optional.get());
            return "Successfully deleted.";
        } else {
            throw new ResourceNotFoundException("There is no artist with id = " + id);
        }
    }
}
