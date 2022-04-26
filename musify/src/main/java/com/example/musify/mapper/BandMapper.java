package com.example.musify.mapper;

import com.example.musify.dto.BandDTO;
import com.example.musify.model.Band;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BandMapper {

    @Mapping(target="bandMembersIds",expression = "java(band.getArtistsIds())")
    BandDTO toDto(Band band);

    List<BandDTO> toDTOList(List<Band> bands);

    Band toEntity(BandDTO bandDTO);
}
