package com.example.musify.mapper;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.model.Album;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumDTO toDto(Album album);

    List<AlbumDTO> toDTOList(List<Album> albums);

    Album toEntity(AlbumDTO albumDTO);
}
