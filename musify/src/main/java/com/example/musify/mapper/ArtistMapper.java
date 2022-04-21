package com.example.musify.mapper;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.model.Artist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistDTO toDto(Artist artist);

    Artist toEntity(ArtistDTO artistDTO);

    List<ArtistDTO> toDTOList(List<Artist> artists);
}
