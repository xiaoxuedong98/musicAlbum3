package com.cs6650.album.server.bean;

public class AlbumInfo {
    private String artist;
    private String title;
    private String year;

    public AlbumInfo(String artist, String title, String year) {
        this.artist = artist;
        this.title = title;
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }
}

