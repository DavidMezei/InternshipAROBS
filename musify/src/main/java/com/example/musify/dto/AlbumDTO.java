package com.example.musify.dto;

import java.sql.Date;
import java.util.List;

public class AlbumDTO {
    private Integer id;
    private Integer artistId;
    private Integer bandId;
    private String title;
    private String description;
    private String genre;
    private Date releaseDate;
    private String label;
    private List<Integer> songIds;

    public AlbumDTO() {
    }

    public AlbumDTO(Integer id, Integer artistId, Integer bandId, String title, String description, String genre, Date releaseDate, String label, List<Integer> songIds) {
        this.id = id;
        this.artistId = artistId;
        this.bandId = bandId;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.label = label;
        this.songIds = songIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getBandId() {
        return bandId;
    }

    public void setBandId(Integer bandId) {
        this.bandId = bandId;
    }

    public List<Integer> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Integer> songIds) {
        this.songIds = songIds;
    }

    public boolean isOnlyOneOwnerIdSet() {
        boolean artistSet, bandSet;

        artistSet = artistId != null && artistId != 0;

        bandSet = bandId != null && bandId != 0;

        if (artistSet && !bandSet) {
            return true;
        } else return !artistSet && bandSet;
    }
}
