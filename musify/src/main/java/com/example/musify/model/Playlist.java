package com.example.musify.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    private User ownerUser;

    private String type;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToMany(mappedBy = "followedPlaylists")
    private Set<User> users = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "playlists_songs",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private Set<Song> songsInPlaylist = new HashSet<>();

    public Playlist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addSubscribedUser(User user) {
        users.add(user);
        user.getFollowedPlaylists().add(this);
    }

    public void removeSubscribedUser(User user) {
        users.remove(user);
        user.getFollowedPlaylists().remove(this);
    }

    public Set<User> getSubscribedUsers() {
        return users;
    }

    public void setSubscribedUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Song> getSongsInPlaylist() {
        return songsInPlaylist;
    }

    public void setSongsInPlaylist(Set<Song> songsInPlaylist) {
        this.songsInPlaylist = songsInPlaylist;
    }

    public void addSong(Song song) {
        songsInPlaylist.add(song);
        song.getPlaylists().add(this);
    }

    public void removeSong(Song song) {
        songsInPlaylist.remove(song);
        song.getPlaylists().remove(this);
    }

    public void addOwnerUser(User user) {
        ownerUser = user;
        user.getOwnedPlaylists().add(this);
    }

    public void removeOwnerUser(User user) {
        ownerUser = null;
        user.getOwnedPlaylists().remove(this);
    }
}
