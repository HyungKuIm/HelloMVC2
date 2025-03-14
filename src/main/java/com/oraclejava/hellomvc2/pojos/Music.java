package com.oraclejava.hellomvc2.pojos;

import java.time.LocalDateTime;

public class Music {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private String url;
    private LocalDateTime insert_dt;

    public Music() {
    }

    public Music(String title, String artist, String album, String genre) {
        //this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getInsert_dt() {
        return insert_dt;
    }

    public void setInsert_dt(LocalDateTime insert_dt) {
        this.insert_dt = insert_dt;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
