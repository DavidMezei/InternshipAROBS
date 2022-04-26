package com.example.musify.repo;

import com.example.musify.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {

    @Override
    List<Song> findAll();

    List<Song> findAllByTitleContainingIgnoreCase(String searchTerm);
}
