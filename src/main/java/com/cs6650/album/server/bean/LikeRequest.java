package com.cs6650.album.server.bean;


public class LikeRequest {
    private String likeornot;
    private int albumID;

    public LikeRequest(String likeornot, int albumID) {
        this.likeornot = likeornot;
        this.albumID = albumID;
    }

    public String getLikeornot() {
        return likeornot;
    }

    public void setLikeornot(String likeornot) {
        this.likeornot = likeornot;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    @Override
    public String toString() {
        return "LikeRequest{" +
                "likeornot='" + likeornot + '\'' +
                ", albumID='" + albumID + '\'' +
                '}';
    }
}
