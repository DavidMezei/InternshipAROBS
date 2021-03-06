package com.example.musify.repo;

import com.example.musify.model.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends CrudRepository<Band, Integer> {

    List<Band> findAllByBandNameContainingIgnoreCase(String searchTerm);
}
