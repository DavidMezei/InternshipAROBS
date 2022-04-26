package com.example.musify.controller;

import com.example.musify.dto.SearchViewDTO;
import com.example.musify.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/search")
@RestController
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{searchTerm}")
    public ResponseEntity<SearchViewDTO> search(@PathVariable String searchTerm) {
        return new ResponseEntity<>(searchService.search(searchTerm), HttpStatus.OK);
    }
}
