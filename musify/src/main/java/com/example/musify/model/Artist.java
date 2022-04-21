package com.example.musify.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "stage_name")
    private String stageName;
    private Date birthday;
    @Column(name = "activity_start_date")
    private String activityStartDate;
    @Column(name = "activity_end_date")
    private String activityEndDate;

    @ManyToMany()
    @JoinTable(name = "artists_albums",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "album_id") })
    private Set<Album> artistAlbums = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "bands_artists",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "band_id") })
    private Set<Band> bands = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "artists_songs",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private Set<Song> composedSongs = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Set<Band> getBands() {
        return bands;
    }

    public Set<Album> getArtistAlbums() {
        return artistAlbums;
    }

    public void setArtistAlbums(Set<Album> artistAlbums) {
        this.artistAlbums = artistAlbums;
    }

    public Set<Song> getComposedSongs() {
        return composedSongs;
    }

    public void setComposedSongs(Set<Song> composedSongs) {
        this.composedSongs = composedSongs;
    }

    public void addAlbum(Album album) {
        artistAlbums.add(album);
        album.setArtist(this);
    }

    public void removeAlbum(Album album) {
        artistAlbums.remove(album);
        album.setArtist(this);
    }
}
