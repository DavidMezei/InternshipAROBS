package com.example.musify.mapper;

import com.example.musify.dto.SongDTO;
import com.example.musify.model.Song;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDTO toDto(Song song);

    List<SongDTO> toDTOList(List<Song> songs);

    Song toEntity(SongDTO songDTO);
}
