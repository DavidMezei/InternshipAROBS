package com.example.musify.controller;

import com.example.musify.dto.BandDTO;
import com.example.musify.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/band")
@RestController
public class BandController {
    private final BandService bandService;

    @Autowired
    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @PostMapping()
    public ResponseEntity<BandDTO> addBand(@RequestBody BandDTO bandDTO) {
        return new ResponseEntity<>(bandService.addBand(bandDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BandDTO> updateBand(@PathVariable Integer id, @RequestBody BandDTO bandDTO) {
        return new ResponseEntity<>(bandService.updateBand(id, bandDTO), HttpStatus.OK);
    }
}
