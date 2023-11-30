package com.cs6650.album.server;




import com.cs6650.album.server.bean.AlbumInfo;
import com.cs6650.album.server.bean.LikeRequest;
import org.apache.log4j.Logger;

import java.io.*;

import java.sql.SQLException;

public class AlbumsService {
    private static final Logger logger = Logger.getLogger(AlbumsService.class);
    private AlbumsDatabase albumsDatabase = new AlbumsDatabase();
    private static AlbumsService instance = new AlbumsService();

    public static AlbumsService getInstance() {
        return instance;
    }


    public AlbumInfo doGetAlbumByKey(String albumIdStr) throws IOException {
        try {
            int albumId = Integer.parseInt(albumIdStr);
            return albumsDatabase.getAlbumInfoById(albumId);
        } catch (Exception e) {
            logger.error("Failed to fetch album info from database.", e);
            throw new IOException("Database error while fetching album info.", e);
        }
    }


    public String doPostNewAlbum(AlbumInfo albumInfo, long imageSize) throws IOException {
        try {
            int newAlbumId = albumsDatabase.insertNewAlbum(albumInfo, imageSize);
            return String.valueOf(newAlbumId);
        } catch (SQLException e) {
            logger.error("Failed to insert new album into database.", e);
            throw new IOException("Database error while inserting new album.", e);
        }
    }

    public void insertLikeDislike(LikeRequest likeRequest) throws SQLException {
        albumsDatabase.insertAlbumLike(likeRequest.getAlbumID(), likeRequest.getLikeornot());
    }

}

