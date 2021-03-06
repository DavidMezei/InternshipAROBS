package com.example.musify.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bands")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "band_name")
    private String bandName;
    private String location;
    @Column(name = "activity_start_date")
    private String activityStartDate;
    @Column(name = "activity_end_date")
    private String activityEndDate;

    @ManyToMany(mappedBy = "bands")
    private Set<Artist> artists = new HashSet<>();

    @OneToMany(mappedBy = "band")
    private Set<Album> bandAlbums = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(String activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
        artist.getBands().add(this);
    }

    public void removeArtist(Artist artist) {
        this.artists.remove(artist);
        artist.getBands().remove(this);
    }

    public String getBandName() {
        return bandName;
    }

    public String getLocation() {
        return location;
    }

    public Set<Album> getBandAlbums() {
        return bandAlbums;
    }

    public void setBandAlbums(Set<Album> bandAlbums) {
        this.bandAlbums = bandAlbums;
    }

    public void addAlbum(Album album) {
        bandAlbums.add(album);
        album.setBand(this);
    }

    public void removeAlbum(Album album) {
        bandAlbums.remove(album);
        album.setBand(null);
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public List<Integer> getArtistsIds() {
        List<Integer> artistsIds = new ArrayList<>();
        for (Artist artist : artists) {
            artistsIds.add(artist.getId());
        }
        return artistsIds;
    }
}