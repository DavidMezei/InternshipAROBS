package com.example.musify.service;

import com.example.musify.UserUtils;
import com.example.musify.dto.BandDTO;
import com.example.musify.exception.ResourceNotFoundException;
import com.example.musify.exception.UnauthorizedException;
import com.example.musify.mapper.BandMapper;
import com.example.musify.model.Artist;
import com.example.musify.model.Band;
import com.example.musify.repo.ArtistRepository;
import com.example.musify.repo.BandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BandService {
    private final BandRepository bandRepository;
    private final ArtistRepository artistRepository;
    private final BandMapper bandMapper;

    @Autowired
    public BandService(BandRepository bandRepository, BandMapper bandMapper, ArtistRepository artistRepository) {
        this.bandRepository = bandRepository;
        this.artistRepository = artistRepository;
        this.bandMapper = bandMapper;
    }

    @Transactional
    public BandDTO addBand(BandDTO bandDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can create new bands");
        }

        Band band = bandMapper.toEntity(bandDTO);
        if (!bandDTO.getBandMembersIds().isEmpty()) {
            addMembersById(band, bandDTO);
        }

        band = bandRepository.save(band);
        bandDTO = bandMapper.toDto(band);

        return bandDTO;
    }

    @Transactional
    public BandDTO updateBand(Integer id, BandDTO bandDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can update bands");
        }

        Optional<Band> optional = bandRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no band with id = " + id);
        }

        Band band = optional.get();
        band.setBandName(bandDTO.getBandName());
        band.setLocation(bandDTO.getLocation());
        band.setActivityStartDate(bandDTO.getActivityStartDate());
        band.setActivityEndDate(bandDTO.getActivityEndDate());
        if (!bandDTO.getBandMembersIds().isEmpty()) {
            clearMembers(band);
            addMembersById(band, bandDTO);
        }

        band = bandRepository.save(band);
        bandDTO = bandMapper.toDto(band);

        return bandDTO;
    }

    public void clearMembers(Band band) {
        Set<Artist> currentMembers = band.getArtists();
        for (Artist artist : currentMembers) {
            artist.getBands().remove(band);
        }

        band.getArtists().clear();
    }

    public void addMembersById(Band band, BandDTO bandDTO) {
        List<Artist> artists = (List<Artist>) artistRepository.findAllById(bandDTO.getBandMembersIds());
        for (Artist artist : artists) {
            band.addArtist(artist);
        }
    }
}