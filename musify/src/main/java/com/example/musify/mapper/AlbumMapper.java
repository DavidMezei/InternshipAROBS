package com.example.musify.mapper;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    @Mapping(target = "songIds", expression = "java(album.getSongIds())")
    @Mapping(target = "artistId", expression = "java(album.getArtistId())")
    @Mapping(target = "bandId", expression = "java(album.getBandId())")
    AlbumDTO toDto(Album album);

    List<AlbumDTO> toDTOList(List<Album> albums);

    Album toEntity(AlbumDTO albumDTO);
}
