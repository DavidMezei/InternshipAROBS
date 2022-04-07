package com.example.musify.mapper;

import com.example.musify.dto.BandDTO;
import com.example.musify.model.Band;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BandMapper {

    BandDTO toDto(Band band);

    Band toEntity(BandDTO bandDTO);
}
