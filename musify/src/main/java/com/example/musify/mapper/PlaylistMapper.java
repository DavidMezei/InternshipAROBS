package com.example.musify.mapper;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.model.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    @Mapping(target = "ownerUserId", expression = "java(playlist.getOwnerUser().getId())")
    PlaylistDTO toDto(Playlist playlist);

    List<PlaylistDTO> toDTOList(List<Playlist> playlists);

    Playlist toEntity(PlaylistDTO playlistTO);
}