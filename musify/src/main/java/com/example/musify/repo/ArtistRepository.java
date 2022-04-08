package com.example.musify.repo;

import com.example.musify.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    @Override
    List<Artist> findAll();
}
